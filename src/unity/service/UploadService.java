package unity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.util.ByteUtil;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import unity.meta.TagGameMeta;
import unity.meta.UploadedDataFragmentMeta;
import unity.meta.UserMeta;
import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.model.ThumbNailData;
import unity.model.UploadedDataFragment;
import unity.model.User;
import unity.model.api.Game;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class UploadService {
    private static final int FRAGMENT_SIZE = 900000;
    private UploadedDataFragmentMeta f = UploadedDataFragmentMeta.get();
    private TagService ts = new TagService();
    private GameDataService gs = new GameDataService();

    public GameData upload(Key key, String gameName, String gameURL,
            FileItem gameFile, FileItem thumbNail, String thumbNailURL,
            String contents, String operations, String hpURL, String pass,
            String thumbNailType, String gameType, boolean thumbNailChange,
            boolean gameChange, String fixTag, String code, long twitterId,
            String gameScreenSize, boolean editCode) {

        List<Object> models = new ArrayList<Object>();

        GameData g = getGameData(key, hpURL, gameName);
        Game gg = getGame(key, g);

        // 変更時解説に変化があったらログを残す
        if (g.getCode() != null && !g.getCode().equals(code)) {
            gs.commentaryLog(g.getKey(), code);
        }

        setData(
            gameName,
            editCode,
            contents,
            operations,
            code,
            gameScreenSize,
            g,
            gg);

        setUser(pass, twitterId, g, gg);

        checkGameUrl(
            gameFile,
            gameURL,
            gameType,
            hpURL,
            g,
            gg,
            models,
            gameChange);

        checkThumbNailUrl(
            thumbNail,
            thumbNailURL,
            thumbNailType,
            g,
            gg,
            models,
            thumbNailChange);

        setTags(fixTag, g, gg);

        g = save(g, gg);

        return g;
    }

    public ThumbNailData thumbNailDelete(Key gameKey) {

        ThumbNailData t =
            Datastore.query(ThumbNailData.class, gameKey).asSingle();
        // 更新時あったら削除
        if (t != null) {
            UploadedDataFragment uf =
                Datastore
                    .query(UploadedDataFragment.class)
                    .filter(
                        UploadedDataFragmentMeta.get().uploadDataRef2.equal(t
                            .getKey()))
                    .asSingle();
            if (uf != null) {
                Datastore.delete(uf.getKey());
            }
        }
        return t;
    }

    public void updateStatus(String gameName, long id, String hpURL) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb
            .setOAuthConsumerKey("ywEtN3tDuqZbOf2xlaQ3g")
            .setOAuthConsumerSecret("ObTlOCI0TUyPJVFr9hOephUqHJscMgoVFaYu89zs")
            .setOAuthAccessToken(
                "276331612-QgLKDBDbiksTI6QnIgpod2ZfHGhhHYXYehqS22kk")
            .setOAuthAccessTokenSecret(
                "2yxeq3yMb8blyqvKSp0cIuj1lN9s4v2O9QAluK3QU");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            if (hpURL.isEmpty()) {
                twitter.updateStatus(""
                    + gameName
                    + "が投稿されました。"
                    + "http://unity-games.appspot.com/unitygames/game/ug"
                    + id
                    + "　#UnityGames");
            } else {
                twitter.updateStatus(""
                    + gameName
                    + "が投稿されました。"
                    + hpURL
                    + "　#UnityGames");
            }
        } catch (TwitterException e) {

            e.printStackTrace();
        }
    }

    public void setData(String gameName, boolean editCode, String contents,
            String operations, String code, String gameScreenSize, GameData g,
            Game gg) {

        g.setApiData(gg.getKey());
        g.setGameName(gameName);
        g.setGameScreenSize(gameScreenSize);
        g.setEditable(editCode);
        g.setContents(contents);
        g.setOperations(operations);
        g.setCode(code);
        g.setLastDate(new Date());
        gg.setExplanation(contents);
        gg.setOperations(operations);
        gg.setCode(code);
        gg.setLastDate(new Date());
        gg.setGameId("ug" + g.getKey().getId());
        gg.setGameName(gameName);

        gg.setGameScreenSize(gameScreenSize);

    }

    public void setUser(String pass, long twitterId, GameData g, Game gg) {

        // passが空ならTwittterアカウントで管理
        if (pass.isEmpty()) {
            // ここでTwitterアカウントを登録
            User u =
                Datastore
                    .query(User.class)
                    .filter(UserMeta.get().userId.equal(twitterId))
                    .asSingle();
            g.setTwitterUserKey(u.getKey());
            g.setPass(null);
            gg.setUser(u.getUserName());
        } else {
            g.setPass(pass);
            g.setTwitterUserKey(null);
            gg.setUser("Guest");
        }

    }

    public void checkGameUrl(FileItem gameFile, String gameURL,
            String gameType, String hpURL, GameData g, Game gg,
            List<Object> models, boolean gameChange) {
        if (gameChange) {
            if (gameURL.isEmpty() && hpURL.isEmpty() && gameFile == null) {
                g.setGameURL("http://unity-games.appspot.com/"
                    + "DefaultSet/UnityGames.unity3d");
                g.setGameType("url");

                gg.setGame("http://unity-games.appspot.com/"
                    + "DefaultSet/UnityGames.unity3d");

            } else {

                g.setGameType(gameType);
                g.setHpURL(hpURL);
                g.setGameURL(gameURL);

                if (hpURL.isEmpty()) {
                    gg.setGame(gameURL);
                }
                if (gameURL.isEmpty()) {
                    gg.setGame(hpURL);
                }

                UploadedDataFragment uf1 =
                    Datastore
                        .query(UploadedDataFragment.class)
                        .filter(
                            UploadedDataFragmentMeta.get().uploadDataRef
                                .equal(g.getKey()))
                        .asSingle();
                if (uf1 != null) {
                    Datastore.delete(uf1.getKey());
                }

                setGameDataFragment(gameFile, g, gg, models);
            }
        }
    }

    public void setGameDataFragment(FileItem gameFile, GameData g, Game gg,
            List<Object> models) {
        System.out.println(gameFile.getFileName());
        if (gameFile != null) {
            byte[] bytes = gameFile.getData();
            byte[][] bytesArray1 = ByteUtil.split(bytes, FRAGMENT_SIZE);
            Iterator<Key> keys =
                Datastore
                    .allocateIds(g.getKey(), f, bytesArray1.length)
                    .iterator();
            for (int i = 0; i < bytesArray1.length; i++) {
                byte[] fragmentData = bytesArray1[i];
                UploadedDataFragment fragment = new UploadedDataFragment();
                models.add(fragment);
                fragment.setKey(keys.next());
                fragment.setBytes(fragmentData);
                fragment.setType("GameFile");
                fragment.setIndex(i);
                fragment.getUploadDataRef().setModel(g);
            }
            for (Object model : models) {
                Datastore.put(model);
            }

            gg.setGame("http://unity-games.appspot.com/unitygames/GameData?id="
                + g.getKey().getId());
        }

    }

    public void checkThumbNailUrl(FileItem thumbNail, String thumbNailURL,
            String thumbNailType, GameData g, Game gg, List<Object> models,
            boolean thumbNailChange) {

        if (thumbNailChange) {
            if (thumbNailURL.isEmpty() && thumbNail == null) {
                g.setThumbNailURL("http://unity-games.appspot.com/"
                    + "DefaultSet/UnityGames.png");
                g.setThumbNailType("url");
                gg.setThumbNail("http://unity-games.appspot.com/"
                    + "DefaultSet/UnityGames.png");

            } else {
                g.setThumbNailURL(thumbNailURL);
                g.setThumbNailType(thumbNailType);

                if (thumbNail == null) {
                    gg.setThumbNail(thumbNailURL);
                }

                // 更新時あったら削除
                ThumbNailData t = thumbNailDelete(g.getKey());

                // creating new
                createThumbNail(thumbNail, g.getGameName(), t, g, gg, models);

            }
        }
    }

    public void createThumbNail(FileItem thumbNail, String gameName,
            ThumbNailData t, GameData g, Game gg, List<Object> models) {
        List<ThumbNailData> list = new ArrayList<ThumbNailData>();
        Key keyss = Datastore.allocateId(g.getKey(), ThumbNailData.class);

        ThumbNailData child = new ThumbNailData();
        child.setKey(keyss);
        child.setGameName(gameName);
        child.setDate(new Date());
        System.out.println("nam:" + thumbNail.getFileName());
        if (thumbNail != null) {
            g.setThumbNailURL(null);
            if (t != null) {
                Datastore.delete(t.getKey());
            }
            child.setLength(thumbNail.getData().length);
            byte[] bytes2 = thumbNail.getData();
            byte[][] bytesArray2 = ByteUtil.split(bytes2, FRAGMENT_SIZE);
            Iterator<Key> keys2 =
                Datastore
                    .allocateIds(g.getKey(), f, bytesArray2.length)
                    .iterator();
            for (int i = 0; i < bytesArray2.length; i++) {
                byte[] fragmentData2 = bytesArray2[i];
                UploadedDataFragment fragment2 = new UploadedDataFragment();
                models.add(fragment2);
                fragment2.setKey(keys2.next());
                fragment2.setBytes(fragmentData2);
                fragment2.setType("ThumbNail");

                fragment2.setIndex(i);
                fragment2.getUploadDataRef2().setModel(child);
            }

            list.add(child);
            gg
                .setThumbNail("http://unity-games.appspot.com/unitygames/thumbNail?id="
                    + g.getKey().getId());
        }
        Datastore.put(list);

        for (Object model : models) {
            Datastore.put(model);
        }
    }

    public void setTags(String fixTag, GameData g, Game gg) {

        if (g.getFixTags() != null || gg.getFixTags() != null) {
            g.getFixTags().clear();
            gg.getFixTags().clear();
        }
        List<Key> tgs =
            Datastore
                .query(TagGame.class)
                .filter(TagGameMeta.get().gameRef.equal(g.getKey()))
                .asKeyList();
        for (Key key2 : tgs) {
            Transaction tx = Datastore.beginTransaction();
            Datastore.delete(key2);
            tx.commit();
        }
        String[] tags = new String[0];
        if (fixTag != null) {
            tags = fixTag.split(",");
        }
        for (String t : tags) {
            t = t.trim();
            if (t.isEmpty()) {
                continue;
            }
            Tag tag2 = ts.getTag(t);

            if (g.getFixTags() == null || gg.getFixTags() == null) {

                g.setFixTags(new HashSet<Tag>());
                gg.setFixTags(new HashSet<Tag>());
            }
            g.getFixTags().add(tag2);
            gg.getFixTags().add(tag2);
            TagGame tt = new TagGame();
            tt.getGameRef().setKey(g.getKey());
            tt.getTagRef().setModel(tag2);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(tt);
            tx.commit();
        }

    }

    public GameData getGameData(Key key, String hpURL, String gameName) {
        GameData g = null;

        if (key == null) {
            // newGame
            g = new GameData();
            g.setDate(new Date());
            Key gamekey = Datastore.allocateId(GameData.class);
            g.setKey(gamekey);
         
            // つぶやき
             updateStatus(gameName, gamekey.getId(), hpURL);

        } else {
            // updateGame
            g = Datastore.get(GameData.class, key);
        }
        return g;
    }

    public Game getGame(Key key, GameData g) {

        Game gg = null;
        if (key == null) {
            // newGame
            gg = new Game();
            gg.setEntry(new Date());
            Key gameKey = Datastore.allocateId(g.getKey(), Game.class);

            gg.setKey(gameKey);
        } else {
            // updateGame
            gg = Datastore.get(Game.class, g.getApiData());
        }

        return gg;
    }

    public byte[] getBytes(GameData uploadedData) {
        if (uploadedData == null) {
            throw new NullPointerException(
                "The uploadedData parameter must not be null.");
        }

        List<UploadedDataFragment> fragmentList =
            uploadedData.getFragmentListRef().getModelList();
        byte[][] bytesArray2 = new byte[fragmentList.size()][0];
        for (int i = 0; i < fragmentList.size(); i++) {
            bytesArray2[i] = fragmentList.get(i).getBytes();

        }

        return ByteUtil.join(bytesArray2);
    }

    public byte[] getBytes2(ThumbNailData uploadedData2) {
        if (uploadedData2 == null) {
            throw new NullPointerException(
                "The uploadedData parameter must not be null.");
        }

        List<UploadedDataFragment> fragmentList2 =
            uploadedData2.getFragmentListRef().getModelList();
        byte[][] bytesArray2 = new byte[fragmentList2.size()][0];
        for (int i = 0; i < fragmentList2.size(); i++) {
            bytesArray2[i] = fragmentList2.get(i).getBytes();

        }

        return ByteUtil.join(bytesArray2);
    }

    public GameData save(GameData g, Game gg) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.put(gg);
        tx.commit();
        return g;
    }

}
