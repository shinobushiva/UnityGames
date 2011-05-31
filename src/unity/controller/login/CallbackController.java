package unity.controller.login;

import unity.service.ChangeService;
import unity.service.UserService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

public class CallbackController extends Controller {
    private UserService us = new UserService();
    private ChangeService service = new ChangeService();

    @Override
    public Navigation run() throws Exception {
        // セッションからtwitterオブジェクトとRequestTokenの取出
        Twitter twitter = (Twitter) sessionScope("tw");
        RequestToken requestToken = (RequestToken) sessionScope("requestToken");
        String verifier = request.getParameter("oauth_verifier");

        // AccessTokenの取得
        try {
            twitter.getOAuthAccessToken(requestToken, verifier);

            // if(sessionScope("loginType"))
            sessionScope("twitter", twitter);
            removeSessionScope("tw");
            removeSessionScope("requestToken");

        } catch (TwitterException e) {
            e.printStackTrace();
        }

        us.regist(
            twitter.getId(),
            twitter.getScreenName(),
            twitter.showUser(twitter.getId()).getDescription());
        // ユーザーのIDを設定しておく なんのため？忘れた
        sessionScope("userId", twitter.getId());

        if (sessionScope("loginType") != null)
            if (sessionScope("loginType").equals("newGame")) {

                service.change(
                    null,
                    (String) sessionScope("gameName"),
                    (String) sessionScope("gameURL"),
                    (FileItem) sessionScope("gameFile"),
                    (FileItem) sessionScope("thumbNail"),
                    (String) sessionScope("thumbNailURL"),
                    (String) sessionScope("contents"),
                    (String) sessionScope("operations"),
                    (String) sessionScope("hpURL"),
                    "",
                    (String) sessionScope("thumbNailType"),
                    (String) sessionScope("gameType"),
                    "",
                    "",
                    (String) sessionScope("fixTag"),
                    (String) sessionScope("code"),
                    (long) twitter.getId());

                removeSessionScope("gameName");
                removeSessionScope("gameURL");
                removeSessionScope("gameFile");
                removeSessionScope("thumbNail");
                removeSessionScope("thumbNailURL");
                removeSessionScope("contents");
                removeSessionScope("operations");
                removeSessionScope("hpURL");
                removeSessionScope("thumbNailType");
                removeSessionScope("gameType");
                removeSessionScope("fixTag");
                removeSessionScope("code");
                removeSessionScope("twitter");
                removeSessionScope("loginType");
                return forward("/unitygames/upload/uploaded.jsp");

            }

        String userName = (String) sessionScope("userName");
        removeSessionScope("loginType");
        removeSessionScope("userName");
        return redirect("/user/" + userName);
    }
}