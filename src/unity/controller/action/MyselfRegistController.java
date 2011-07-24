package unity.controller.action;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.UserService;

public class MyselfRegistController extends Controller {
    private UserService us = new UserService();

    @Override
    public Navigation run() throws Exception {

        us.setWebUrl(asLong("userId"), asString("myselfText"));

        return null;
    }
}
