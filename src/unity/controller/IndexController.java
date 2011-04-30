package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;
import unity.model.vo.CampaignVo;
import unity.service.CampaignService;

public class IndexController extends Controller {
    private GameDataMeta g = GameDataMeta.get();
    private CampaignService cs = new CampaignService();

    @Override
    public Navigation run() throws Exception {
        // キャンペーン
        List<CampaignVo> cvos = cs.loadCampaigns();
        requestScope("campaigns", cvos);
       
        //ランキング
        List<GameData> rankingGame =
            Datastore.query(g).sort(g.point.desc).sort(g.date.desc).limit(5).asList();
        
                requestScope("rankingGameList", rankingGame);
        
        // 新着ゲーム
        List<GameData> newGame =
            Datastore.query(g).sort(g.date.desc).limit(5).asList();
        requestScope("newGameList", newGame);

        return forward("index.jsp");
    }
}
