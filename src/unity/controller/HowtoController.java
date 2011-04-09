package unity.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class HowtoController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("howto.jsp");
    }
}
