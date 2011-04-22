package unity.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import jp.co.topgate.controller.JsonController;

import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;

import com.google.appengine.api.datastore.KeyFactory;

public class GameLoadController extends JsonController {
    private GameDataMeta dd = GameDataMeta.get();

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));

        if (g.getGameURL().isEmpty()) {
            map.put(
                "play",
                "unityObject.embedUnity('unityPlayer','/unitygames/GameData?id="
                    + g.getKey().getId()
                    + "', 600, 450);");

        } else {
            map.put(
                "play",
                "unityObject.embedUnity('unityPlayer','"
                    + g.getGameURL()
                    + "', 600, 450);");
        }

        return map;
    }
}
