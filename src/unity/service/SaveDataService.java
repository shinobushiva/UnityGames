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

    public List<GameSaveData> getSaveData(List<GameSaveData> saveData,
            Key gkey, Long userId) {

        Key ukey = Datastore.createKey(User.class, userId);
        saveData =
            Datastore
                .query(GameSaveData.class)
                .filter(GameSaveDataMeta.get().gameRef.equal(gkey))
                .filter(GameSaveDataMeta.get().userRef.equal(ukey))
                .asList();

        return saveData;

    }

    public List<GameSaveData> getSaveData(List<GameSaveData> saveData, Key gkey) {

        return Datastore
            .query(GameSaveData.class)
            .filter(GameSaveDataMeta.get().gameRef.equal(gkey))
            .asList();
    }

    public void setSaveData(Long gameId, Long userId, String data) {
        Key gkey = Datastore.createKey(GameData.class, gameId);
        Key ukey = null;
        if (userId != null) {
            ukey = Datastore.createKey(User.class, userId);
        }
        GameSaveDataMeta s = GameSaveDataMeta.get();
        GameSaveData gsd =
            Datastore
                .query(GameSaveData.class)
                .filter(s.gameRef.equal(gkey))
                .filter(s.userRef.equal(ukey))
                .asSingle();
        if (gsd == null) {
            gsd = new GameSaveData();
            gsd.setKey(Datastore.allocateId(gkey, GameSaveData.class));
            if (userId != null) {
                gsd.getUserRef().setKey(ukey);
            }
            gsd.getGameRef().setKey(gkey);
        }
        gsd.setSaveData(data);

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(gsd);
        tx.commit();
    }

    public SaveLoadId getId(Long gameId) {

        Key gameKey = Datastore.createKey(GameData.class, gameId);

        return getSaveLoadId(gameKey);
    }

    public SaveLoadId getSaveLoadId(Key key) {

        return Datastore.query(SaveLoadId.class, key).asSingle();

    }
}
