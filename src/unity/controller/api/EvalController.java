package unity.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.api.GameSaveData;
import unity.service.SaveDataService;

public class EvalController extends Controller {
    private SaveDataService sd = new SaveDataService();

    @Override
    public Navigation run() throws Exception {
        System.out.println("loadId : " + asString("loadId"));
        String script = asString("script");
        Long userId = asLong("userId");
        Long gameId = asLong("gameId");
        String loadType = asString("loadType");
        String loadId = sd.getId(gameId).getLoadId();
        if (asString("loadId").equals(loadId)) {
            GameSaveData saveData =
                sd.getSaveData(gameId, userId, loadType);

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            engine.put("data", saveData.getSaveData());
            try {
                Object eval = engine.eval("(" + script + ")();");
                Map<String, Object> evalMap = new HashMap<String, Object>();
                evalMap.put("result", eval);
                response.setContentType("text/javascript");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(JSON.encode(evalMap));

            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
