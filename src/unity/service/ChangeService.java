package unity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.util.ByteUtil;

import unity.meta.TagGameMeta;
import unity.meta.UploadedDataFragmentMeta;
import unity.meta.UserMeta;
import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.model.ThumbNailData;
import unity.model.UploadedDataFragment;
import unity.model.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class ChangeService {
    private static final int FRAGMENT_SIZE = 900000;
    private UploadedDataFragmentMeta f = UploadedDataFragmentMeta.get();
    private static final Logger log = Logger.getLogger(ChangeService.class
        .getName());
    private TagService ts = new TagService();

    public GameData change(Key key, String gameName, String gameURL,
            FileItem gameFile, FileItem thumbNail, String thumbNailURL,
            String contents, String operations, String hpURL, String pass,
            String thumbNailType, String gameType, String thumbNailChange,
            String gameChange, String fixTag, String code, long twitterId) {

        List<Object> models = new ArrayList<Object>();

        GameData g = null;

        if (key == null) {
            g = new GameData();
            g.setDate(new Date());
            key = Datastore.allocateId(GameData.class);
            g.setKey(key);
        } else {
            g = Datastore.get(GameData.class, key);
        }

        g.setGameName(gameName);

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
        } else {
            g.setPass(pass);
            g.setTwitterUserKey(null);
        }
        g.setContents(contents);
        g.setOperations(operations);
        g.setCode(code);
        g.setLastDate(new Date());
        if (gameChange != null) {

            if (gameURL.isEmpty() && hpURL.isEmpty() && gameFile == null) {
                g.setGameURL("http://unity-games.appspot.com/"
                    + "DefaultSet/UnityGames.unity3d");
                g.setGameType("url");
            } else {

                g.setGameType(gameType);
                g.setHpURL(hpURL);
                g.setGameURL(gameURL);

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
                if (gameFile != null) {
                    g.setLength(gameFile.getData().length);
                    byte[] bytes = gameFile.getData();
                    byte[][] bytesArray1 = ByteUtil.split(bytes, FRAGMENT_SIZE);
                    Iterator<Key> keys =
                        Datastore
                            .allocateIds(g.getKey(), f, bytesArray1.length)
                            .iterator();
                    for (int i = 0; i < bytesArray1.length; i++) {
                        byte[] fragmentData = bytesArray1[i];
                        UploadedDataFragment fragment =
                            new UploadedDataFragment();
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
                }
            }
        }
        if (thumbNailChange != null) {

            if (thumbNailURL.isEmpty() && thumbNail == null) {
                g.setThumbNailURL("http://unity-games.appspot.com/"
                    + "DefaultSet/UnityGames.png");
                g.setThumbNailType("url");
            } else {
                g.setThumbNailURL(thumbNailURL);
                g.setThumbNailType(thumbNailType);
                ThumbNailData t =
                    Datastore.query(ThumbNailData.class, g.getKey()).asSingle();
                if (t != null) {
                    UploadedDataFragment uf =
                        Datastore
                            .query(UploadedDataFragment.class)
                            .filter(
                                UploadedDataFragmentMeta.get().uploadDataRef2
                                    .equal(t.getKey()))
                            .asSingle();
                    if (uf != null) {
                        Datastore.delete(uf.getKey());
                    }
                }

                // creating new
                List<ThumbNailData> list = new ArrayList<ThumbNailData>();
                Iterator<Key> keyss =
                    Datastore
                        .allocateIds(g.getKey(), ThumbNailData.class, 1)
                        .iterator();
                while (keyss.hasNext()) {
                    ThumbNailData child = new ThumbNailData();
                    child.setKey(keyss.next());
                    child.setGameName(gameName);
                    child.setDate(new Date());
                    if (thumbNail != null) {
                        g.setThumbNailURL(null);
                        Datastore.delete(t.getKey());
                        child.setLength(thumbNail.getData().length);
                        byte[] bytes2 = thumbNail.getData();
                        byte[][] bytesArray2 =
                            ByteUtil.split(bytes2, FRAGMENT_SIZE);
                        Iterator<Key> keys2 =
                            Datastore.allocateIds(
                                g.getKey(),
                                f,
                                bytesArray2.length).iterator();
                        for (int i = 0; i < bytesArray2.length; i++) {
                            byte[] fragmentData2 = bytesArray2[i];
                            UploadedDataFragment fragment2 =
                                new UploadedDataFragment();
                            models.add(fragment2);
                            fragment2.setKey(keys2.next());
                            fragment2.setBytes(fragmentData2);
                            fragment2.setType("ThumbNail");

                            fragment2.setIndex(i);
                            fragment2.getUploadDataRef2().setModel(child);
                        }

                        list.add(child);
                    }

                    Datastore.put(list);
                    for (Object model : models) {
                        Datastore.put(model);
                    }
                }
            }
        }

        if (g.getFixTags() != null) {
            g.getFixTags().clear();
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
        String[] tags = fixTag.split(",");
        for (String t : tags) {
            t = t.trim();
            if (t.isEmpty()) {
                continue;
            }
            Tag tag2 = ts.getTag(t);
            log.info("1234:" + t);

            if (g.getFixTags() == null) {

                g.setFixTags(new HashSet<Tag>());

            }
            g.getFixTags().add(tag2);

            TagGame tt = new TagGame();
            tt.getGameRef().setKey(g.getKey());
            tt.getTagRef().setModel(tag2);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            Datastore.put(tt);
            tx.commit();
        }

        // Tag tag = Datastore.query(Tag.class, g.getKey()).asSingle();
        // System.out.println("tagu:" + tag.getKey());
        // tag.setFixTag(fixTag);
        // Datastore.put(tag);

        Transaction tx = Datastore.beginTransaction();

        Datastore.put(g);
        tx.commit();

        return g;
    }

}
