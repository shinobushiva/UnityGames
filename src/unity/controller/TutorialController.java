package unity.controller;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.GameDataService;
import unity.service.SearchService;
import unity.service.TagService;

public class TutorialController extends Controller {
    private TagService ts = new TagService();
    private GameDataService gs = new GameDataService();
    private SearchService ss = new SearchService();
    @Override
    public Navigation run() throws Exception {

        List<TagGame> tgl = new ArrayList<TagGame>();
        Tag tag2 = ts.getTag("チュートリアル");
        List<TagGame> tgs = ts.getTagGames(tag2.getKey());

        tgl.addAll(tgs);

        List<GameData> gds = gs.getTutorialGame(tgl);
        requestScope("GameList", gds);
        gs.contentCut(gds);

        // 補完ワード
        requestScope("words", ss.suggestionWords());
        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));
        return forward("tutorial.jsp");
    }
}
