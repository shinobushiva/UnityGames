package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.api.GameSaveDataMeta;
import unity.model.GameData;
import unity.model.User;
import unity.model.api.GameSaveData;
import unity.model.api.SaveLoadId;

import com.google.appengine.api.datastore.Key;

public class SaveDataService {

    public GameSaveData getSaveData(GameSaveData saveData, Key gkey, Long userId) {

        Key ukey = Datastore.createKey(User.class, userId);
        saveData =
            Datastore
                .query(GameSaveData.class)
                .filter(GameSaveDataMeta.get().gameRef.equal(gkey))
                .filter(GameSaveDataMeta.get().userRef.equal(ukey))
                .asSingle();

        return saveData;

    }

    public GameSaveData getSaveData(GameSaveData saveData, Key gkey) {

        return Datastore
            .query(GameSaveData.class)
            .filter(GameSaveDataMeta.get().gameRef.equal(gkey))
            .filter(GameSaveDataMeta.get().saveType.equal("public"))
            .asSingle();
    }

    public void setSaveData(Long gameId, Long userId, String saveType,
            String data) {
        GameSaveDataMeta s = GameSaveDataMeta.get();
        Key gkey = Datastore.createKey(GameData.class, gameId);

        List<GameSaveData> gsds =
            Datastore
                .query(GameSaveData.class)
                .filter(s.gameRef.equal(gkey))
                .asList();

        Key ukey = null;
        if (userId != null) {
            ukey = Datastore.createKey(User.class, userId);
        }
        GameSaveData gsd = null;
        if ("private".equals(saveType.toLowerCase())) {
            // private処理 privateなのでuserId必須
            if (userId != null) {
                for (GameSaveData gs : gsds) {
                    // privateTypeのセーブデータがあったら
                    if ("private".equals(gs.getSaveType())
                        && gs.getUserRef().getKey().equals(ukey)) {
                        gsd = gs;
                        System.out.println("privateTypeのセーブデータがあったら");
                    }
                }
                // privateTypeのセーブデータなかったら
                if (gsd == null) {
                    gsd = new GameSaveData();
                    gsd.setKey(Datastore.allocateId(gkey, GameSaveData.class));
                    gsd.setSaveType(saveType);
                    // gsd.getUserListRef().getModelList().add(Datastore.get(User.class,
                    // ukey));
                    gsd.getUserRef().setKey(ukey);
                    gsd.getGameRef().setKey(gkey);
                    System.out.println("privateTypeのセーブデータなかったら");
                }
                gsd.setSaveData(data);
            }
        }

        if ("public".equals(saveType.toLowerCase())) {
            // public処理
            for (GameSaveData gs : gsds) {
                if ("public".equals(gs.getSaveType())) {
                    gsd = gs;
                    System.out.println("publicあったら");
                }
            }
            if (gsd == null) {
                gsd = new GameSaveData();
                gsd.setKey(Datastore.allocateId(gkey, GameSaveData.class));
                gsd.setSaveType(saveType);
                gsd.getGameRef().setKey(gkey);
                System.out.println("publicなかったら");
            }
            gsd.setSaveData(data);
        }

        System.out.println(gsd.getKey());
        System.out.println(gsd.getSaveData());
        System.out.println(gsd.getUserRef().getKey());
        System.out.println("-------------------------------");
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(gsd);
        tx.commit();
    }

    public GameSaveData getSaveData(Long gameId, Long userId, String loadType) {

        GameSaveData saveData = null;

        Key gkey = Datastore.createKey(GameData.class, gameId);

        if ("private".toLowerCase().equals(loadType))
            saveData = getSaveData(saveData, gkey, userId);
        if ("public".toLowerCase().equals(loadType))
            saveData = getSaveData(saveData, gkey);

        // List<GameSaveDataVo> list = new ArrayList<GameSaveDataVo>();
        // for (GameSaveData d : saveData) {
        // GameSaveDataVo dv = new GameSaveDataVo();
        // dv.setSaveData(d.getSaveData());
        // list.add(dv);
        // }
        return saveData;
    }

    public SaveLoadId getId(Long gameId) {

        Key gameKey = Datastore.createKey(GameData.class, gameId);

        return getSaveLoadId(gameKey);
    }

    public SaveLoadId getSaveLoadId(Key key) {

        return Datastore.query(SaveLoadId.class, key).asSingle();

    }
}
