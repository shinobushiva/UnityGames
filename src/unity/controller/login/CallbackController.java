package unity.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;
import unity.model.SessionGameData;
import unity.service.UploadService;
import unity.service.UserService;

import com.google.appengine.api.datastore.Key;

public class CallbackController extends Controller {
    private UserService us = new UserService();
    private UploadService service = new UploadService();

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

        if (sessionScope("loginType") != null) {
            String uc = "";
            Key sessionKey = sessionScope("sessionKey");
            Key key = sessionScope("key");
            System.out.println("sessionKey" + sessionKey);
            System.out.println("key:" + key);
            System.out.println("gfile:" + sessionScope("gameFile"));
            System.out.println("gamechange:"
                + (String) sessionScope("gameChange"));

            System.out.println();
            SessionGameData sg =
                Datastore.get(SessionGameData.class, sessionKey);

            System.out.println("size:" + sg.getGameScreenSize());

            String gameChange = null;
            String thumbNailChange = null;
            if (sessionScope("loginType").equals("newGame")) {
                gameChange = "";
                thumbNailChange = "";
            } else {

                gameChange = sessionScope("gameChange");
                thumbNailChange = sessionScope("thumbNailChange");
            }
System.out.println("sgfix:"+sg.getFixTags());
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
                sg.getGameScreenSize());

            if (sessionScope("loginType").equals("newGame")) {

                uc = "upload/uploaded";

            } else {
                removeSessionScope("key");
                removeSessionScope("thumbNailChange");
                removeSessionScope("gameChange");

                uc = "changed";
            }
            removeSessionScope("gameFile");
            removeSessionScope("thumbNail");
            removeSessionScope("loginType");
            removeSessionScope("sessionKey");

            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.delete(sg.getKey());
            tx.commit();
            return redirect("/unitygames/" + uc);
        }

        String userName = (String) sessionScope("userName");
        removeSessionScope("userName");
        return redirect("/user/" + userName);
    }
}