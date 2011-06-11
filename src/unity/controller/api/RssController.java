package unity.controller.api;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.api.Game;
import unity.service.ApiService;

public class RssController extends Controller {

    private ApiService as = new ApiService();
    
    @Override
    public Navigation run() throws Exception {
        
        List<Game> findAll = as.findAll();
        
//        XStream xml = (XStream) findAll;
//        
//        System.out.println(xml);
        
        return null;
    }
}
