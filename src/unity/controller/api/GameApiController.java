package unity.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.topgate.controller.JsonController;
import unity.model.api.Game;
import unity.service.ApiService;

public class GameApiController extends JsonController {

    private ApiService as = new ApiService();

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> data = new HashMap<String, Object>();
        String id = asString("id");

        if (!id.equals("all")) {
            Game list = as.find(id);
            data.put("gameData", list);

        } else {

            List<Game> list = as.findAll();
            data.put("gameData", list);

        }

        return data;
    }
}
