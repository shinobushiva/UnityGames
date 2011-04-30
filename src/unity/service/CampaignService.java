package unity.service;

import java.util.ArrayList;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.CampaignGameMeta;
import unity.meta.CampaignMeta;
import unity.model.Campaign;
import unity.model.CampaignGame;
import unity.model.vo.CampaignVo;

public class CampaignService {

    public void save(Campaign c) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(c);
        tx.commit();

    }

    public List<CampaignVo> loadCampaigns() {
        List<CampaignVo> cvos = new ArrayList<CampaignVo>();

        List<Campaign> loadCampaigns = Datastore
        .query(Campaign.class)
        .filter(CampaignMeta.get().state.equal(Campaign.STATE_NOW))
        .asList();
        // System.out.println(loadCampaigns);

        for (Campaign c : loadCampaigns) {

            CampaignVo cvo = new CampaignVo();
            cvos.add(cvo);
            cvo.setStart(c.getStart());
            cvo.setEnd(c.getEnd());
            cvo.setTitle(c.getTitle());
            cvo.setState(c.getState());

            List<CampaignGame> cgs =
                Datastore
                    .query(CampaignGame.class)
                    .filter(
                        CampaignGameMeta.get().campaignRef.equal(c.getKey()))
                    .asList();
            for (CampaignGame g : cgs) {
                cvo.getGames().add(g.getGameRef().getModel());

            }
            break;
        }
        return cvos;

    }
    public void save(CampaignGame cg){
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(cg);
        tx.commit();
        
    }
    
}
