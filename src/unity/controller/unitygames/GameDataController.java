package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.GameData;
import unity.service.GameDataService;
import unity.service.UploadService;

public class GameDataController extends Controller {
    private UploadService service = new UploadService();
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {

        GameData g = gs.getGameData(asLong("id"));

        if (g.getGameURL() != null) {
            requestScope("url", g.getGameURL());
        }

        byte[] bytes = service.getBytes(g);
        requestScope("key", g.getKey());
        show(g.getGameName() + ".unity3d", bytes);

        return null;
    }
}
