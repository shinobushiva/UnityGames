package unity.controller.action;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class TwitterController extends Controller {

    @Override
    public Navigation run() throws Exception {
        System.out.println();
        String header = request.getHeader("Authorization");
        return null;
    }
}
