package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sun.org.apache.bcel.internal.generic.NEW;

import unity.model.Campaign;
import unity.model.GameData;
import unity.service.CampaignService;

public class CampaignListController extends Controller {

    private CampaignService cs = new CampaignService();

    @Override
    public Navigation run() throws Exception {
        
        
        
        List<Campaign> loadCampaigns = cs.loadCampaigns();
  //何が入っているか確認      
        for (Campaign campaign : loadCampaigns) {
            System.out.println(campaign.getGames());
            for (GameData g : campaign.getGames()) {
                System.out.println(g.getGameName());               
                
            }
        }
        
        requestScope("campaigns", loadCampaigns);

        return forward("campaignList.jsp");
    }
}
