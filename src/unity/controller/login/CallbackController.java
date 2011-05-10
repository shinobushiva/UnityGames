package unity.controller.login;

import unity.service.UserService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

public class CallbackController extends Controller {
    private UserService us = new UserService();

    @Override
    public Navigation run() throws Exception {
        // セッションからtwitterオブジェクトとRequestTokenの取出
        Twitter twitter = (Twitter) sessionScope("tw");
        RequestToken requestToken = (RequestToken) sessionScope("requestToken");
        String verifier = request.getParameter("oauth_verifier");

        // AccessTokenの取得
        try {
            twitter.getOAuthAccessToken(requestToken, verifier);
            sessionScope("twitter", twitter);
            removeSessionScope("tw");
            removeSessionScope("requestToken");

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        us.regist(twitter.getId(), twitter.getScreenName(),twitter.showUser(twitter.getId()).getDescription());
        // ユーザーのIDを設定しておく なんのため？忘れた
        sessionScope("userId", twitter.getId());
        return redirect("/");
    }
}