package unity.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import jp.co.topgate.controller.JsonController;
import unity.model.User;
import unity.service.UserService;

public class MyselfLoadController extends JsonController {
    private UserService us = new UserService();

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        long id = asLong("id");

        User user = us.getUser(id);

        map.put("user", user);
        return map;
    }

}
