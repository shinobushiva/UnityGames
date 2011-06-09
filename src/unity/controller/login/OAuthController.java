package unity.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class OAuthController extends Controller {

    @Override
    public Navigation run() throws Exception {

        if ("true".equals(requestScope("useReferer")))
            sessionScope("referer", request.getHeader("REFERER"));
        // このファクトリインスタンスは再利用可能でスレッドセーフです
        // twitterオブジェクトをセッションに格納
        Twitter twitter = new TwitterFactory().getInstance();
        sessionScope("tw", twitter);

        sessionScope("userName", asString("name"));

        // callback用のURLを生成して格納
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL
            .replace(index, callbackURL.length(), "")
            .append("/callback");

        // RequestTokenを取得してセッションに格納、アプリケーション認可画面に移動
        RequestToken requestToken =
            twitter.getOAuthRequestToken(callbackURL.toString());
        request.getSession().setAttribute("requestToken", requestToken);

        return redirect(requestToken.getAuthenticationURL());
    }
}
