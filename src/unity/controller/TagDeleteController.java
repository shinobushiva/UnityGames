package unity.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.GameData;
import unity.model.Tag;
import unity.service.GameDataService;
import unity.service.TagService;

public class TagDeleteController extends Controller {
    // private GameDataMeta dd = GameDataMeta.get();
    private TagService ts = new TagService();
    GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {

        Tag tag = ts.getTag(asLong("tagId"));
        GameData g = gs.getGameData(asLong("gameKey"));

        ts.deleteTagGame(g.getKey(), tag.getKey());
        gs.deleteTagGame(g, tag);

        return null;
    }
}
