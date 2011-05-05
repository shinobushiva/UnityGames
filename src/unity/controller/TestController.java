package unity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.topgate.controller.JsonController;
import net.arnx.jsonic.JSON;
import unity.model.GameData;
import unity.service.ApiService;

public class TestController extends JsonController {

    private ApiService as = new ApiService();

    @Override
    protected Map<String, Object> handle() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        String id = asString("id");

        String text = "";
        if (!id.equals("all")) {
            Long ids = Long.parseLong(id);
            GameData list = as.find(ids);
            text = JSON.encode(list);
        }
        if (id.equals("all")) {
            List<GameData> list = as.findAll();
            text = JSON.encode(list);
        }

        // String text = JSON.encode(list);

        data.put("gameData", text);

        return data;
    }
}
