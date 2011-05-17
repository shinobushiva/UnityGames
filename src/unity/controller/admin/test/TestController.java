package unity.controller.admin.test;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.GameData;

public class TestController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        List<GameData> asList = Datastore.query(GameData.class).asList();
        System.out.println(asList);
        requestScope("GameList",asList);
        
        return forward("test.jsp");
    }
}
