package unity.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.GameData;
import unity.model.api.GameSaveData;
import unity.model.vo.GameSaveDataVo;
import unity.service.SaveDataService;

import com.google.appengine.api.datastore.Key;

public class EvalController extends Controller {
    private SaveDataService sds = new SaveDataService();

    @Override
    public Navigation run() throws Exception {
        System.out.println("loadId : "+asString("loadId"));
        String script = asString("script");
        Long userId = asLong("userId");
        Long gameId = asLong("gameId");
        String loadId = sds.getId(gameId).getLoadId();
        if (asString("loadId").equals(loadId)) {
            List<GameSaveData> saveData = null;

            Key gkey = Datastore.createKey(GameData.class, gameId);

            if (userId != null) {
                saveData = sds.getSaveData(saveData, gkey, userId);
            } else {

                saveData = sds.getSaveData(saveData, gkey);

            }

            List<GameSaveDataVo> list = new ArrayList<GameSaveDataVo>();
            for (GameSaveData d : saveData) {
                GameSaveDataVo dv = new GameSaveDataVo();
                dv.setSaveData(d.getSaveData());
                list.add(dv);
            }

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            engine.put("data", list);
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
