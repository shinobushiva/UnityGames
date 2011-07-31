package unity.controller.unitygames;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.GameData;
import unity.service.GameDataService;

public class ViewController extends Controller {
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {
        removeSessionScope("viewType");
        System.out.println("あれーーーー");
        int data = 0;
        if (!asString("view").isEmpty() || asString("view") != null)
            sessionScope("viewType", asString("view"));
        else
            sessionScope("viewType", data);

        System.out.println(asString("view"));
        System.out.println(data);
        System.out.println(sessionScope("viewType"));

        if (sessionScope("viewType") != null) {
            data = Integer.parseInt((String) sessionScope("viewType"));
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
