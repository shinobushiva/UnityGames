package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import twitter4j.ProfileImage;
import twitter4j.Twitter;
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
        // Twitterアカウント情報
        // ローカル対策 OAuth認証できないので俺のアカウント画像とか直接指定
        if ("127.0.0.1".equals(request.getRemoteHost())) {
            requestScope("user.userName", "kyusyukeigo");
            requestScope(
                "p",
                "http://a1.twimg.com/profile_images/1243088874/PzH_28_bigger.jpg");
            requestScope("login", "no");

            // キャンペーン
            List<CampaignVo> cvos = cs.loadCampaigns();
            requestScope("campaigns", cvos);

            // ランキング
            List<GameData> rankingGame =
                Datastore
                    .query(g)
                    .sort(g.point.desc)
                    .sort(g.date.desc)
                    .limit(5)
                    .asList();

            requestScope("rankingGameList", rankingGame);

            // 新着ゲーム
            List<GameData> newGame =
                Datastore.query(g).sort(g.date.desc).limit(5).asList();
            requestScope("newGameList", newGame);

            return forward("index.jsp");
        }
        if (sessionScope("twitter") == null) {
            requestScope("login", "no");
        } else {
            requestScope("login", "yes");
        }
        // // modelのUser情報取得
        // User user = us.getUser((Long) sessionScope("userId"));
        // requestScope("user", user);
        // Twitter側から情報取得
        Twitter twitter = (Twitter) sessionScope("twitter");
        ProfileImage profileimage = null;
        // Twitter画像URL取得
        requestScope(
            "p",
            twitter.getProfileImage(
                twitter.getScreenName(),
                profileimage.BIGGER).getURL());
        // キャンペーン
        List<CampaignVo> cvos = cs.loadCampaigns();
        requestScope("campaigns", cvos);

        // ランキング
        List<GameData> rankingGame =
            Datastore
                .query(g)
                .sort(g.point.desc)
                .sort(g.date.desc)
                .limit(5)
                .asList();

        requestScope("rankingGameList", rankingGame);

        // 新着ゲーム
        List<GameData> newGame =
            Datastore.query(g).sort(g.date.desc).limit(5).asList();
        requestScope("newGameList", newGame);

        return forward("index.jsp");
    }
}
