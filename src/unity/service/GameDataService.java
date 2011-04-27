package unity.service;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.CampaignMeta;
import unity.model.Campaign;
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
