package unity.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.ProfileImage;
import twitter4j.Twitter;
import unity.model.User;
import unity.service.UserService;

public class IndexController extends Controller {
    private UserService us = new UserService();

    @Override
    public Navigation run() throws Exception {
        // ローカル対策 OAuth認証できないので俺のアカウント画像とか直接指定
        if ("127.0.0.1".equals(request.getRemoteHost())) {
            requestScope("user.userName", "kyusyukeigo");
            requestScope("p", "http://a1.twimg.com/profile_images/"
                + "1243088874/PzH_28_normal.jpg");
            return forward("index.jsp");
        }

        // modelのUser情報取得
        User user = us.getUser((Long) sessionScope("userId"));
        requestScope("user", user);
        // Twitter側から情報取得
        Twitter twitter = (Twitter) sessionScope("twitter");
        ProfileImage profileimage = null;
        // Twitter画像URL取得
        requestScope(
            "p",
            twitter.getProfileImage(
                twitter.getScreenName(),
                profileimage.NORMAL).getURL());

        return forward("/index.jsp");
    }
}
