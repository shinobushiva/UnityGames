package unity.controller.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.GameData;
import unity.model.Tag;

public class CampaignController extends Controller {

    @Override
    public Navigation run() throws Exception {

        List<GameData> g = Datastore.query(GameData.class).asList();
        requestScope("GameList", g);

       Set<String> name = new HashSet<String>();
        for (GameData gameData : g) {
         
            for(Tag t : gameData.getFixTags()){
               name.add(t.getName());
            }
            
        }
        requestScope("tagName",name);
        return forward("campaign.jsp");
    }
}
