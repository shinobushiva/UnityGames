package unity.controller.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.topgate.controller.JsonController;

import org.slim3.datastore.Datastore;

import unity.meta.CommentMeta;
import unity.meta.GameDataMeta;
import unity.model.Comment;
import unity.model.GameData;

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
        // 日本時間に修正
//        for (Comment co : comments) {
//            long l = co.getDate().getTime() + 1000 * 60 * 60 * 9;
//            co.getDate().setTime(l);
//        }

        map.put("comments", comments);
        return map;
    }

}
