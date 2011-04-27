package unity.controller.admin;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.Campaign;
import unity.model.GameData;
import unity.service.CampaignService;
import unity.service.GameDataService;

public class CampaignRegistrationController extends Controller {
    private GameDataService gds = new GameDataService();
    private CampaignService cs = new CampaignService();

    @Override
    public Navigation run() throws Exception {

        String title = asString("title");
        String[] ga = requestScope("gameArray");

        Campaign c = new Campaign();

        c.setTitle(title);
        for (String s : ga) {
            GameData gameData = gds.load(Long.parseLong(s));
            c.getGames().add(gameData);

        }
        c.setState(Campaign.STATE_NOW);

        cs.save(c);

        return null;
    }
}
