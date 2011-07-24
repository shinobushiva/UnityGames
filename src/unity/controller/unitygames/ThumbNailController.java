package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.ThumbNailData;
import unity.service.ThumbNailService;
import unity.service.UploadService;

public class ThumbNailController extends Controller {

    private UploadService service = new UploadService();
    private ThumbNailService ts = new ThumbNailService();

    @Override
    public Navigation run() throws Exception {

        // 親のキーから探してきている
        ThumbNailData u = ts.getThumbNailData(asLong("id"));
        if (u != null) {
            byte[] bytes = service.getBytes2(u);
            show(u.getGameName(), bytes);
        }
        return null;
    }
}
