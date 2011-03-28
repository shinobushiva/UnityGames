package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import unity.model.GameData;
import unity.service.UploadService;

public class GameDataController extends Controller {
    private UploadService service = new UploadService();
    @Override
    public Navigation run() throws Exception {
        
        String key = asString("key");
        System.out.println(key);
        Key keyy = KeyFactory.stringToKey(key);

        GameData u = Datastore.get(GameData.class, keyy);
        Key upk = u.getKey();
//        Key upKey = KeyFactory.stringToKey(upk);

        if(u.getGameURL() != null){
        
          requestScope("url", u.getGameURL()) ;
            
        }
        
        
        GameData data = Datastore.get(GameData.class, upk);
        byte[] bytes = service.getBytes(data);
        System.out.println(data.getGameName());
        requestScope("key", key);
        show(data.getGameName()+".unity3d", bytes);
        
        
        
        return null;
    }
}
