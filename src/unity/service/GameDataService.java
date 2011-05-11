package unity.service;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;

public class GameDataService {

    public void save(GameData c) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(c);
        tx.commit();

    }

    public GameData load(long id) {

        return Datastore.get(
            GameData.class,
            Datastore.createKey(GameData.class, id));

    }

    public GameData addPoint(GameData g) {

        int point = g.getAccess() + g.getComment() * 3;

        g.setPoint(point);

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.commit();

        return null;
    }
}
