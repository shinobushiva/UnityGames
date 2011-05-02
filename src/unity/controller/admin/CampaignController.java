package unity.controller.admin;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.GameData;

public class CampaignController extends Controller {

    @Override
    public Navigation run() throws Exception {

        List<GameData> g = Datastore.query(GameData.class).asList();
        requestScope("GameList", g);
        return forward("campaign.jsp");
    }
}
