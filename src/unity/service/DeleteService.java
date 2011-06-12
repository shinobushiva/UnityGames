package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import unity.meta.TagGameMeta;
import unity.meta.api.GameMeta;
import unity.model.GameData;
import unity.model.TagGame;
import unity.model.api.Game;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class DeleteService {

    public GameData delete(Key key) {
        GameData g = Datastore.get(GameData.class, key);
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

        Transaction tx = Datastore.beginTransaction();
        Datastore.deleteAll(g.getKey());
        tx.commit();

        return null;
    }

    public Game deleteApi(Key key) {
        String id = String.valueOf(key.getId());
        Game g =
            Datastore
                .query(Game.class)
                .filter(GameMeta.get().gameId.equal(id))
                .asSingle();

        Transaction tx = Datastore.beginTransaction();
        Datastore.deleteAll(g.getKey());
        tx.commit();

        return null;

    }
}
