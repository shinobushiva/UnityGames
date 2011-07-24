package unity.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;
import unity.model.Tweet;
import unity.model.User;
import unity.service.GameDataService;
import unity.service.LoginService;
import unity.service.UserService;

public class IndexController extends Controller {
    private LoginService ls = new LoginService();
    private UserService us = new UserService();
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {

        String name = asString("name");

        boolean isMe = false;

        if (ls.isLogin(request)) {
            String userName = ls.getLoginUserName(request);
            if (name.equals(userName)) {
                requestScope("showUser", true);

                if (asString("edit") != null) {
                    if (asString("edit").equals("edit"))
                        isMe = true;
                } else {
                    isMe = false;
                }
            }

        }

        // アカウント名からモデルのuserIdを取り出す
        User uk = us.getName(name);
        requestScope("twitterId", uk.getUserId());

        // TweetIdを取り出す
        Set<Tweet> tweets = uk.getTweets();

        // ローカルの時だけ全取得
        // List<GameData> g = Datastore.query(GameData.class).asList();

        // 登録ゲームを取得(とりあえず新着順)
        List<GameData> g = gs.myGameList(uk);

        // 登録ゲームのリスト
        requestScope("gameList", g);

        // webUrlを表示
        // 該当したtweetのリスト
        requestScope("tweet", tweets);
        // モデルのUser情報
        requestScope("um", uk);
        requestScope("user", name);
        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));

        // 表示だけが違うのでここでjspを判別する
        if (!isMe) {
            for (GameData gg : g) {
                gs.setUgLink(gg);
            }
            return forward("/showUser/otherPage.jsp");
        }
        return forward("/showUser/myPage.jsp");
    }
}
