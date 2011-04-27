package unity.service;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.CampaignMeta;
import unity.model.Campaign;

public class CampaignService {

    public void save(Campaign c) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(c);
        tx.commit();

    }

    public List<Campaign> loadCampaigns() {

        return Datastore
            .query(Campaign.class)
            .filter(CampaignMeta.get().state.equal(Campaign.STATE_NOW))
            .asList();

    }
}
