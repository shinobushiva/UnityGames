package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class ChangeController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("change.jsp");
    }
}
