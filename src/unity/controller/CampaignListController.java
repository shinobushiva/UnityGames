package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.vo.CampaignVo;
import unity.service.CampaignService;

public class CampaignListController extends Controller {

    private CampaignService cs = new CampaignService();

    @Override
    public Navigation run() throws Exception {

        List<CampaignVo> cvos =   cs.loadCampaigns();

        requestScope("campaigns", cvos);

        return forward("campaignList.jsp");
    }
}
