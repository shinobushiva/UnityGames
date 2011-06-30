package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.GameData;
import unity.model.ThumbNailData;
import unity.service.UploadService;

import com.google.appengine.api.datastore.Key;

public class ThumbNailController extends Controller {

    private UploadService service = new UploadService();

    @Override
    public Navigation run() throws Exception {

        long id = asLong("id");
        Key key = Datastore.createKey(GameData.class, id);

        // 親のキーから探してきている
        ThumbNailData u = Datastore.query(ThumbNailData.class, key).asSingle();
        if (u != null) {
            Key upk = u.getKey();

            ThumbNailData data = Datastore.get(ThumbNailData.class, upk);

            byte[] bytes = service.getBytes2(data);
            show(data.getGameName(), bytes);
        }
        requestScope("ThumbNailURL", null);
        return null;
    }
}
