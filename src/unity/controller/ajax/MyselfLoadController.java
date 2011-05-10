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

public class MyselfLoadController extends JsonController {
    private GameDataMeta dd = GameDataMeta.get();

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        long id = asLong("id");

        User user = Datastore.query(User.class).filter(UserMeta.get().userId.equal(id)).asSingle();
        
//userモデルのweb情報とmyself情報を同時取得

        map.put("user", user);
        return map;
    }

}
