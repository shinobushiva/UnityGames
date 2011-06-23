package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

      //ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));

        return forward("index.jsp");
    }
}
