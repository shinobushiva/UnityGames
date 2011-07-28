package unity.controller.api;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.SaveDataService;

public class SaveDataController extends Controller {
    private SaveDataService sds = new SaveDataService();

    @Override
    public Navigation run() throws Exception {
        System.out.println("saveId : "+asString("saveId"));
        
        Long gameId = asLong("gameId");
        String saveId = sds.getId(gameId).getSaveId();
        
        if (asString("saveId").equals(saveId))
            sds.setSaveData(gameId, asLong("userId"),asString("saveType"), asString("data"));

        return null;
    }
}
