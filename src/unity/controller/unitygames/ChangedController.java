package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class ChangedController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("changed.jsp");
    }
}
