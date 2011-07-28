package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.GameData;
import unity.service.GameDataService;

public class ViewController extends Controller {
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {
        String data = "Default";
        if (!asString("view").isEmpty() || asString("view") != null)
            sessionScope("viewType", asString("view"));
        else
            sessionScope("viewType", data);
        
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
