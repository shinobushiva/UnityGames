package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import unity.model.GameData;
import unity.model.Note;


public class GameController extends Controller {

   
    
    @Override
    public Navigation run() throws Exception {
        
        
        String key = asString("key");
        Key keyy = KeyFactory.stringToKey(key);

        requestScope("key", key);
        
        GameData u = Datastore.get(GameData.class, keyy);
        
        Note n = Datastore.get(Note.class,u.getNoteKey());
        
        requestScope("url",u.getGameURL());   
        requestScope("Contents",n.getContents());
        requestScope("Operations",n.getOperations());
    
        if(u.getGameURL().isEmpty()){
            requestScope("play","unityObject.embedUnity('unityPlayer','/unitygames/GameData?key="+key+"', 600, 450);");
            
        }else{
          requestScope("play","unityObject.embedUnity('unityPlayer','"+u.getGameURL()+"', 600, 450);");
        }
     
        
        
        return forward("Game.jsp");
    }
}
