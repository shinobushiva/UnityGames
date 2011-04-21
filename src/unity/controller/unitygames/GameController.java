package unity.controller.unitygames;

import java.net.URI;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import unity.meta.CommentMeta;
import unity.meta.GameDataMeta;
import unity.meta.TagMeta;
import unity.model.Comment;
import unity.model.GameData;
import unity.model.Tag;

public class GameController extends Controller {

    private GameDataMeta dd = GameDataMeta.get();

    @Override
    public Navigation run() throws Exception {

        String remoteAddr = request.getRemoteAddr();

        System.out.println(remoteAddr);

        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));
        requestScope("key", g.getKey());
        if (!remoteAddr.equals(Memcache.get("lastIp-" + g.getGameName()))) {
            Memcache.put("lastIp-" + g.getGameName(), remoteAddr);
            Transaction tx = Datastore.beginTransaction();
            g.setAccess(g.getAccess() + 1);
            Datastore.put(g);
            tx.commit();
        }
        requestScope("g", g);

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

        return forward("game.jsp");
    }
}
