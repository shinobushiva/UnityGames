package unity.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import jp.co.topgate.controller.JsonController;

import org.slim3.datastore.Datastore;

import unity.meta.UserMeta;
import unity.model.User;

public class MyselfLoadController extends JsonController {

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        long id = asLong("id");

        User user =
            Datastore
                .query(User.class)
                .filter(UserMeta.get().userId.equal(id))
                .asSingle();

        // userモデルのweb情報とmyself情報を同時取得

        map.put("user", user);
        return map;
    }

}
