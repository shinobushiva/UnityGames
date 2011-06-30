package unity.controller.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.topgate.controller.JsonController;

import org.slim3.datastore.Datastore;

import unity.meta.CommentMeta;
import unity.meta.GameDataMeta;
import unity.meta.UserMeta;
import unity.model.Comment;
import unity.model.GameData;
import unity.model.User;

import com.google.appengine.api.datastore.KeyFactory;

public class CommentLoadController extends JsonController {
    private GameDataMeta dd = GameDataMeta.get();

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));

        // コメント表示
        List<Comment> comments =
            Datastore
                .query(Comment.class, g.getKey())
                .sort(CommentMeta.get().date.desc)
                .asList();

        for (Comment comment : comments) {
            String userName = "Guest";
            if (!comment.getTwitterId().isEmpty()) {
                Long userId = Long.valueOf((comment.getTwitterId()));
                User user =
                    Datastore
                        .query(User.class)
                        .filter(UserMeta.get().userId.equal(userId))
                        .asSingle();
                userName = user.getUserName();
            }
            comment.setTwitterId(userName);
        }

        map.put("comments", comments);
        return map;
    }
}
