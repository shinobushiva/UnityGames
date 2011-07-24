package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;
import unity.service.GameDataService;

public class ViewController extends Controller {
    private GameDataMeta g = GameDataMeta.get();
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {

        if (!asString("view").isEmpty())
            sessionScope("viewType", asString("view"));

        String data = "Default";
        if (sessionScope("viewType") != null) {
            data = sessionScope("viewType");
        }

        List<GameData> games = gs.getViewPattern(data);

        requestScope("GameList", games);
        gs.contentCut(games);
        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));
        return forward("view.jsp");
    }
}
