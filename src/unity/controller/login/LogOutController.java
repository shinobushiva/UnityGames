package unity.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class LogOutController extends Controller {

    @Override
    public Navigation run() throws Exception {

        removeSessionScope("isLogin");
        removeSessionScope("loginUser");
        
        return redirect(request.getHeader("REFERER"));
    }
}
