package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import unity.model.ThumbNailData;
import unity.service.UploadService;

public class ThumbNailController extends Controller {

    private UploadService service = new UploadService();

    @Override
    public Navigation run() throws Exception {

        String thumbNailKey = asString("thumbNailKey");
        Key key = KeyFactory.stringToKey(thumbNailKey);

        ThumbNailData u = Datastore.query(ThumbNailData.class, key).asSingle();
        Key upk = u.getKey();

        ThumbNailData data = Datastore.get(ThumbNailData.class, upk);

        byte[] bytes = service.getBytes2(data);
        show(data.getGameName(), bytes);
        requestScope("ThumbNailURL", null);
        return null;
    }
}
