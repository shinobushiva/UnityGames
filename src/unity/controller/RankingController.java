package unity.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.GameDataService;
import unity.service.SearchService;

public class RankingController extends Controller {
    private SearchService ss = new SearchService();
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {

       

        // ランキング
        requestScope("rankingGameList", gs.rankingGame(50));

        // 補完ワード
        requestScope("words", ss.suggestionWords());
     // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));
        return forward("ranking.jsp");
    }
}
