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
        unity.model.api.Game gg = null;
        GameData g = null;

        if (key == null) {
            g = new GameData();
            g.setDate(new Date());
            key = Datastore.allocateId(GameData.class);
            g.setKey(key);
      
            gg = new unity.model.api.Game();
            gg.setEntry(new Date());
            Key key2 = Datastore.allocateId(key,unity.model.api.Game.class);
            gg.setKey(key2);
            g.setApiData(key2);
        } else {
            g = Datastore.get(GameData.class, key);
          if(g.getApiData() !=null){
            gg= Datastore.get(unity.model.api.Game.class,g.getApiData());
          }else{
              gg = new unity.model.api.Game();
          }
          }

        g.setGameName(gameName);
        gg.setGameName(gameName);
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
        g.setContents(contents);
        g.setOperations(operations);
        g.setCode(code);
        g.setLastDate(new Date());
        
        gg.setExplanation(contents);
        gg.setOperations(operations);
        gg.setCode(code);
        gg.setLastDate(new Date());
        
        if (gameChange != null) {

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
                
                if(hpURL.isEmpty()){
                    gg.setGame(gameURL);     
                }
                if(gameURL.isEmpty()){
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
                
                if(gameFile != null){

                    gg.setGame("http://unity-games.appspot.com/unitygames/GameData?id="+g.getKey().getId());
                    
                }
                
            }
        }
        if (thumbNailChange != null) {

            if (thumbNailURL.isEmpty() && thumbNail == null) {
                g.setThumbNailURL("http://unity-games.appspot.com/"
                    + "DefaultSet/UnityGames.png");
                g.setThumbNailType("url");
 
                gg.setThumbNail("http://unity-games.appspot.com/"
                    + "DefaultSet/UnityGames.png");
                
            } else {
                g.setThumbNailURL(thumbNailURL);
                g.setThumbNailType(thumbNailType);
                
                if(thumbNail == null){
                gg.setThumbNail(thumbNailURL);
                }
                ThumbNailData t =
                    Datastore.query(ThumbNailData.class, g.getKey()).asSingle();
               //更新時あったら削除
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
               Key keyss =
                    Datastore
                        .allocateId(g.getKey(), ThumbNailData.class);
                        
                    ThumbNailData child = new ThumbNailData();
                    child.setKey(keyss);
                    child.setGameName(gameName);
                    child.setDate(new Date());
                    if (thumbNail != null) {
                        g.setThumbNailURL(null);
                        if(t !=null){
                        Datastore.delete(t.getKey());
                        }
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
                    if(thumbNail != null){
                        
                        gg.setThumbNail("http://unity-games.appspot.com/unitygames/thumbNail?id="+g.getKey().getId());
                        
                    }
                }
            }
        

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
        String[] tags = fixTag.split(",");
        for (String t : tags) {
            t = t.trim();
            if (t.isEmpty()) {
                continue;
            }
            Tag tag2 = ts.getTag(t);
            log.info("1234:" + t);

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

     
       
        
        gg.setGameId("ug"+g.getKey().getId());
        
        
        
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.put(gg);
        tx.commit();

        return g;
    }

}
