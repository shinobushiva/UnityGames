package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.TagGameMeta;
import unity.meta.api.GameMeta;
import unity.model.GameData;
import unity.model.TagGame;
import unity.model.api.Game;

import com.google.appengine.api.datastore.Key;

public class DeleteService {

    public void delete(Key key) {
        GameData g = Datastore.get(GameData.class, key);
        List<Key> tgs =
            Datastore
                .query(TagGame.class)
                .filter(TagGameMeta.get().gameRef.equal(g.getKey()))
                .asKeyList();
        for (Key key2 : tgs) {
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.delete(key2);
            tx.commit();
        }

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.deleteAll(g.getKey());
        tx.commit();

    }

    public void deleteApi(Key key) {
        String id = "ug" + String.valueOf(key.getId());

        List<Key> keys =
            Datastore
                .query(Game.class)
                .filter(GameMeta.get().gameId.equal(id))
                .asKeyList();

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.delete(keys);
        tx.commit();

    }
}
