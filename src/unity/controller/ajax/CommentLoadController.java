package unity.controller.ajax;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.CommentMeta;
import unity.meta.GameDataMeta;
import unity.model.Comment;
import unity.model.GameData;

import com.google.appengine.api.datastore.KeyFactory;

public class CommentLoadController extends Controller {
    private GameDataMeta dd = GameDataMeta.get();

    @Override
    public Navigation run() throws Exception {

        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));

        // コメント表示
        List<Comment> comment =
            Datastore
                .query(Comment.class, g.getKey())
                .sort(CommentMeta.get().date.asc)
                .asList();
        requestScope("c", comment);
        for (Comment co : comment) {
            long l = co.getDate().getTime() + 1000 * 60 * 60 * 9;
            co.getDate().setTime(l);
        }

        return forward("commentLoad.jsp");
    }
}
