package unity.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import jp.co.topgate.controller.JsonController;
import unity.model.GameData;
import unity.service.GameDataService;

public class CodeSaveController extends JsonController {
    private GameDataService gs = new GameDataService();

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        String commentary = asString("codeEditArea");

        GameData g = gs.getGameData(asLong("id"));

        if (!g.isEditable()) {
            return map;
        }

        // ログとして残す
        if (!g.getCode().equals(commentary))
            gs.commentaryLog(g.getKey(), commentary);

        gs.setCode(g, commentary);

        map.put("code", gs.toCodeJson(commentary));

        return map;
    }
}
