package unity.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.CampaignGameMeta;
import unity.model.Campaign;
import unity.model.CampaignGame;
import unity.model.vo.CampaignVo;
import unity.service.CampaignService;

public class CampaignListController extends Controller {

    private CampaignService cs = new CampaignService();

    @Override
    public Navigation run() throws Exception {

        List<CampaignVo> cvos = new ArrayList<CampaignVo>();

        List<Campaign> loadCampaigns = cs.loadCampaigns();
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

        requestScope("campaigns", cvos);

        return forward("campaignList.jsp");
    }
}
