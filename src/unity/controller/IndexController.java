package unity.controller;

import java.util.List;
import java.util.logging.Logger;

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

    // private UserService us = new UserService();

    @Override
    public Navigation run() throws Exception {

        Logger Log = Logger.getLogger(request.getRemoteAddr());

        Log.info("RemoteAddr" + request.getRemoteAddr());

        // キャンペーン
        List<CampaignVo> cvos = cs.loadCampaigns();
        requestScope("campaigns", cvos);

        // ランキング
        List<GameData> rankingGame = Datastore.query(g).sort(g.point.desc)
        // .sort(g.date.desc)
            .limit(5)
            .asList();

        requestScope("rankingGameList", rankingGame);

        // 新着ゲーム
        List<GameData> newGame =
            Datastore.query(g).sort(g.date.desc).limit(5).asList();
        requestScope("newGameList", newGame);
        //ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));
        return forward("index.jsp");
    }
}
