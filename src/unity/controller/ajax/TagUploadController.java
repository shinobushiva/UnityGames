package unity.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import jp.co.topgate.controller.JsonController;

import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;

import com.google.appengine.api.datastore.KeyFactory;

public class TagUploadController extends JsonController {

    private GameDataMeta dd = GameDataMeta.get();

    @Override
    protected Map<String, Object> handle() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));
        map.put("fixTags", g.getFixTags());
        map.put("tags", g.getTags());
        return map;
    }
}
