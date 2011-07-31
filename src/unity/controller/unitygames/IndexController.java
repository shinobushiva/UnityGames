package unity.controller.unitygames;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;
import unity.service.GameDataService;
import unity.service.SearchService;

public class IndexController extends Controller {

    private GameDataMeta g = GameDataMeta.get();
    private GameDataService gs = new GameDataService();
    private SearchService ss = new SearchService();

    // private NoteMeta n = NoteMeta.get();

    @Override
    public Navigation run() throws Exception {
        // ゲーム新着順
        List<GameData> games =
            Datastore
                .query(g)
                .filter(g.accessLevel.equal(0))
                .limit(50)
                .sort(g.access.desc)
                .asList();

        gs.contentCut(games);

        requestScope("GameList", games);

        int data = 0;
        if (sessionScope("viewType") != null)
            data = Integer.parseInt((String) sessionScope("viewType"));

        requestScope("viewType", data);

        // 補完ワード
        requestScope("words", ss.suggestionWords());
        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));

        return forward("index.jsp");
    }
}
