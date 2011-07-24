package unity.controller;

import java.net.URLDecoder;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.GameDataService;
import unity.service.TagService;

public class TagRegistController extends Controller {
    private TagService ts = new TagService();
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {

        String tagR = asString("tag");
        tagR = URLDecoder.decode(tagR, "UTF-8");

        if (!tagR.isEmpty()) {

            GameData g = gs.getGameData(asLong("gameKey"));

            Tag tag2 = ts.getTag(tagR);

            if (g.getTags() == null) {
                gs.createTags(g);
            }
            gs.addTag(g, tag2);
            // 二度手間してるけど力尽きたので放置・・・6/14
            ts.conflictTag(g.getKey());
            ts.setRelation(g.getKey());
            ts.deleteRelationTag(g.getKey());

            TagGame tt = ts.getTagGame(g.getKey(), tag2.getKey());
            if (tt == null) {
                ts.createTagGame(g, tag2);
            }

        }

        return null;
    }
}
