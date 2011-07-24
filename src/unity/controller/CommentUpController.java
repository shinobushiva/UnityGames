package unity.controller;

import java.net.URLDecoder;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import twitter4j.Twitter;
import unity.meta.CommentMeta;
import unity.model.GameData;
import unity.service.CommentService;

import com.google.appengine.api.datastore.Key;

public class CommentUpController extends Controller {
    private CommentService cmt = new CommentService();

    @Override
    public Navigation run() throws Exception {

        String com = URLDecoder.decode(asString("comment"), "UTF-8");
        Twitter twitter = (Twitter) sessionScope("twitter");
        String twitterId = "";
        if (twitter != null)
            twitterId = "" + twitter.getId();

        Key key = Datastore.createKey(GameData.class, asLong("gameKey"));

        if (!com.isEmpty()) {
            cmt.commentUp(key, com, twitterId);
        }

        requestScope(
            "comments",
            CommentMeta.get().modelsToJson(cmt.getCommentsDesc(key).toArray()));
        return null;
    }
}
