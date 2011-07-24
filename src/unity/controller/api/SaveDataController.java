package unity.controller.api;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.SaveDataService;

public class SaveDataController extends Controller {
    private SaveDataService sds = new SaveDataService();

    @Override
    public Navigation run() throws Exception {
        System.out.println("saveId : "+asString("saveId"));
        
        System.out.println(asString("data"));
        Long gameId = asLong("gameId");

        String saveId = sds.getId(gameId).getSaveId();
        System.out.println("DsaveId : "+saveId);
        if (asString("saveId").equals(saveId))
            sds.setSaveData(gameId, asLong("userId"), asString("data"));

        return null;
    }
}
