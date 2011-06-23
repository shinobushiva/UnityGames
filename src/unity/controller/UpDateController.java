package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.GameData;
import unity.service.TagService;

public class UpDateController extends Controller {

    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {
        List<GameData> game = Datastore.query(GameData.class).asList();
        for (GameData g : game) {

            // 二度手間してるけど力尽きたので放置・・・6/14
//            ts.conflictTag(g.getKey());
            ts.setRelation(g.getKey());
       //     ts.deleteRelationTag(g.getKey());
        }
        return null;
    }
}
