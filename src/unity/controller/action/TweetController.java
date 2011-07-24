package unity.controller.action;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;

public class TweetController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String tweet = asString("tweet");
        boolean b = asBoolean("check");
        Twitter twitter = (Twitter) sessionScope("twitter");
        if (!b)
            twitter.updateStatus(tweet + " #UnityGames");

        else
            twitter.updateStatus(tweet);

        return null;
    }
}
