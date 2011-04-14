package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class UploadedController extends Controller {

    @Override
    public Navigation run() throws Exception {

        return forward("uploaded.jsp");
    }
}
