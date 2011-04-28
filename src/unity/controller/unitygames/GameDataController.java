package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;
import unity.service.UploadService;

import com.google.appengine.api.datastore.KeyFactory;

public class GameDataController extends Controller {
    private UploadService service = new UploadService();
    private GameDataMeta dd = GameDataMeta.get();

    @Override
    public Navigation run() throws Exception {

        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));

        // String key = asString("key");
        // System.out.println(key);
        // Key keyy = KeyFactory.stringToKey(key);

        // / Key upKey = KeyFactory.stringToKey(upk);

        if (g.getGameURL() != null) {

            requestScope("url", g.getGameURL());

        }

        GameData data = Datastore.get(GameData.class, g.getKey());
        byte[] bytes = service.getBytes(data);
        System.out.println(data.getGameName());
        requestScope("key", g.getKey());
        show(data.getGameName() + ".unity3d", bytes);

        return null;
    }
}
