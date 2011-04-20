package unity.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.CommentMeta;
import unity.model.Comment;
import unity.model.GameData;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class CommentUpController extends Controller {

    @Override
    public Navigation run() throws Exception {

        System.out.println("コメントアップ読み込み");

        String co = requestScope("a");
        String Ga = requestScope("b");

        String com = co.substring(8);
        com = URLDecoder.decode(com, "UTF-8");

        String gameKeyy = Ga.substring(8);
        System.out.println("コメント：" + com);
        System.out.println("きい" + gameKeyy);
        Key key = KeyFactory.stringToKey(gameKeyy);

        System.out.println(key);
        if (!com.isEmpty()) {
            Key commentKey = Datastore.allocateId(key, Comment.class);
            Comment comment = new Comment();

            comment.setGameKey(key);

            comment.setKey(commentKey);
            comment.setComment(com);
            comment.setDate(new Date());
            GameData g = Datastore.get(GameData.class, key);
            g.setComment(g.getComment() + 1);
            Datastore.put(g);
            Transaction tx = Datastore.beginTransaction();
            Datastore.put(comment);
            tx.commit();

        }

        List<Comment> comments =
            Datastore
                .query(Comment.class)
                .filter(CommentMeta.get().gameKey.equal(key))
                .sort(CommentMeta.get().date.desc)
                .asList();
        requestScope("comments", CommentMeta.get().modelsToJson(comments.toArray()));
        System.out.println(requestScope("comments"));
        return null;
    }
}
