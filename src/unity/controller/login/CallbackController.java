package unity.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import unity.model.GameData;
import unity.model.SessionGameData;
import unity.service.TagService;
import unity.service.UploadService;
import unity.service.UserService;

import com.google.appengine.api.datastore.Key;

public class CallbackController extends Controller {
    private UserService us = new UserService();
    private UploadService service = new UploadService();
    private TagService ts = new TagService();

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
            Key sessionKey = sessionScope("sessionKey");
            Key key = sessionScope("key");
            SessionGameData sg =
                Datastore.get(SessionGameData.class, sessionKey);

            String gameChange = null;
            String thumbNailChange = null;
            if (sessionScope("loginType").equals("newGame")) {
                gameChange = "";
                thumbNailChange = "";
            } else {

                gameChange = sessionScope("gameChange");
                thumbNailChange = sessionScope("thumbNailChange");
            }
            GameData g =
                service.upload(
                    key,
                    sg.getGameName(),
                    sg.getGameURL(),
                    (byte[]) sessionScope("gameFile"),
                    (byte[]) sessionScope("thumbNail"),
                    sg.getThumbNailURL(),
                    sg.getContents(),
                    sg.getOperations(),
                    sg.getHpURL(),
                    "",
                    sg.getThumbNailType(),
                    sg.getGameType(),
                    thumbNailChange,
                    gameChange,
                    sg.getFixTags(),
                    sg.getCode(),
                    (long) twitter.getId(),
                    sg.getGameScreenSize(),
                    sg.isEditCode());
            // 二度手間してるけど力尽きたので放置・・・6/14
            ts.conflictTag(g.getKey());
            ts.setRelation(g.getKey());
            ts.deleteRelationTag(g.getKey());
            if (!sessionScope("loginType").equals("newGame")) {
                removeSessionScope("key");
                removeSessionScope("thumbNailChange");
                removeSessionScope("gameChange");

            }
            removeSessionScope("gameFile");
            removeSessionScope("thumbNail");
            removeSessionScope("loginType");
            removeSessionScope("sessionKey");

            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.delete(sg.getKey());
            tx.commit();
            return redirect("/unitygames/game/ug" + g.getKey().getId());
        }

        String referer = sessionScope("referer");
        removeSessionScope("referer");
        boolean isLogin = sessionScope("isLogin");
        System.out.println("isLogin:" + isLogin);
        requestScope("isLogin", isLogin);
        return redirect(referer);
        // return null;
    }
}