package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import twitter4j.Twitter;
import unity.model.GameData;
import unity.model.User;
import unity.service.CommentService;
import unity.service.GameDataService;
import unity.service.SearchService;
import unity.service.UserService;

public class GameController extends Controller {

    private GameDataService gs = new GameDataService();
    private SearchService ss = new SearchService();
    private UserService us = new UserService();
    private CommentService cms = new CommentService();

    @Override
    public Navigation run() throws Exception {
        Twitter twitter = (Twitter) sessionScope("twitter");
        String remoteAddr = request.getRemoteAddr();
        Boolean isMe = false;
        // gameIdからgameを持ってくる
        GameData g = gs.load(asLong("id"));

        requestScope("key", g.getKey());

        if (!remoteAddr.equals(Memcache.get("lastIp-" + g.getGameName()))) {
            Memcache.put("lastIp-" + g.getGameName(), remoteAddr);
            gs.addAccessPoint(g);
            gs.addPoint(g);
        }
        // 投稿者 Twitterアカウント表示
        if (g.getTwitterUserKey() != null) {
            User uk = us.getUser(g.getTwitterUserKey());
            requestScope("twitterId", uk.getUserId());
            if(twitter!=null){
            if (uk.getUserId() == twitter.getId())
                isMe = true;
            }
        }

        gs.setUgLink(g);

        requestScope("jscode", gs.toCodeJson(g.getCode()));

        String[] split = g.getGameScreenSize().split(",");
        requestScope("width", split[0]);
        requestScope("height", split[1]);

        requestScope("g", g);
        // コメント部分

        requestScope("c", cms.getCommentsAsc(g.getKey()));

        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", twitter);
        if (twitter != null) {
            requestScope("user", us.getUser(twitter.getId()));
        }
        // 補完ワード
        requestScope("words", ss.suggestionWords());
        requestScope("tags", ss.suggestionTags());

        // 編集ボタン
        requestScope("isMe", isMe);
        return forward("game.jsp");
    }
}
