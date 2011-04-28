package unity.service;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;

public class GameDataService {

    public void save(GameData c) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(c);
        tx.commit();

    }

    public GameData load(long id) {

        return Datastore.get(
            GameData.class,
            Datastore.createKey(GameData.class, id));

    }
}
