package unity.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import unity.service.UserService;

public class CallbackController extends Controller {
    private UserService us = new UserService();

    // private UploadService service = new UploadService();
    // private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {
        // セッションからtwitterオブジェクトとRequestTokenの取出
        Twitter twitter = (Twitter) sessionScope("twitter");
        RequestToken requestToken = (RequestToken) sessionScope("requestToken");
        String verifier = request.getParameter("oauth_verifier");

        // AccessTokenの取得
        try {

            AccessToken oAuthAccessToken =
                twitter.getOAuthAccessToken(requestToken, verifier);
            System.out.println("Callback:" + oAuthAccessToken);
            removeSessionScope("requestToken");

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        sessionScope("loginUser", twitter.getScreenName());
        sessionScope("isLogin", true);

        // ユーザー登録
        us.regist(
            twitter.getId(),
            twitter.getScreenName(),
            twitter.showUser(twitter.getId()).getDescription());


        if (sessionScope("loginType") != null) {
            return redirect("/unitygames/upload/newGame");
        }

        String referer = sessionScope("referer");
        removeSessionScope("referer");
        boolean isLogin = sessionScope("isLogin");
        System.out.println("isLogin:" + isLogin);
        requestScope("isLogin", isLogin);
        return redirect(referer);
    }
}