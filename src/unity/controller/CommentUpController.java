package unity.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import twitter4j.Twitter;
import unity.meta.CommentMeta;
import unity.model.Comment;
import unity.model.GameData;
import unity.service.GameDataService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class CommentUpController extends Controller {
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {

        String com = asString("comment");
        com = URLDecoder.decode(com, "UTF-8");
        String gameKey = asString("gameKey");
        Twitter twitter = (Twitter) sessionScope("twitter");
        String twitterId = "";
        if (twitter != null)
            twitterId = "" + twitter.getId();

        Key key = KeyFactory.stringToKey(gameKey);

        System.out.println(key);
        if (!com.isEmpty()) {
            Key commentKey = Datastore.allocateId(key, Comment.class);
            Comment comment = new Comment();

            comment.setGameKey(key);

            comment.setKey(commentKey);
            comment.setComment(com);
            comment.setDate(new Date());
            comment.setTwitterId("" + twitterId);
            GameData g = Datastore.get(GameData.class, key);
            g.setComment(g.getComment() + 1);
            Datastore.put(g);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(comment);
            tx.commit();
            gs.addPoint(g);
        }

        List<Comment> comments =
            Datastore
                .query(Comment.class)
                .filter(CommentMeta.get().gameKey.equal(key))
                .sort(CommentMeta.get().date.desc)
                .asList();
        requestScope(
            "comments",
            CommentMeta.get().modelsToJson(comments.toArray()));
        return null;
    }
}
