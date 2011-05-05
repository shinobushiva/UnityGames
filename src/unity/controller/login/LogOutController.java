package unity.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.UserService;

public class LogOutController extends Controller {

    @Override
    public Navigation run() throws Exception {

        removeSessionScope("twitter");
        removeSessionScope("userId");
        
        return null;
    }
}
