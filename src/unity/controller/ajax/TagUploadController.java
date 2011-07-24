package unity.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import jp.co.topgate.controller.JsonController;
import unity.model.GameData;
import unity.service.GameDataService;

public class TagUploadController extends JsonController {

    private GameDataService gs = new GameDataService();

    @Override
    protected Map<String, Object> handle() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        GameData g = gs.getGameData(asLong("id"));
        map.put("fixTags", g.getFixTags());
        map.put("tags", g.getTags());
        return map;
    }
}
