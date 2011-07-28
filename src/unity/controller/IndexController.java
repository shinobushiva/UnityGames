package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.GameData;
import unity.model.vo.CampaignVo;
import unity.service.CampaignService;
import unity.service.GameDataService;
import unity.service.RankingService;
import unity.service.SearchService;

public class IndexController extends Controller {
    private CampaignService cs = new CampaignService();
    private SearchService ss = new SearchService();
    private GameDataService gs = new GameDataService();
    private RankingService rs = new RankingService();

    @Override
    public Navigation run() throws Exception {
        List<CampaignVo> loadCampaigns = cs.loadCampaigns();
        for (CampaignVo campaignVo : loadCampaigns) {
            List<GameData> contentCut = gs.contentCut(campaignVo.getGames());
            campaignVo.setGames(contentCut);
        }
        // キャンペーン
        requestScope("campaigns", loadCampaigns);

        // ランキング
        requestScope("rankingGameList", gs.rankingGame(5));

        // 新着ゲーム
        requestScope("newGameList", gs.newGame());

        // 補完ワード
        
        requestScope("words", ss.suggestionWords());

        // 動画ランキング
        requestScope("movie", rs.movieRanking());
        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));
        return forward("index.jsp");
    }
}
