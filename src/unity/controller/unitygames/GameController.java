package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import unity.model.GameData;
import unity.model.Note;


public class GameController extends Controller {

   
    
    @Override
    public Navigation run() throws Exception {
        
        
        String key = asString("key");
        Key keyy = KeyFactory.stringToKey(key);

        requestScope("key", key);
        
        GameData u = Datastore.get(GameData.class, keyy);
        
//        Note n = Datastore.get(Note.class,u.getNoteKey());
        
        
        Transaction tx = Datastore.beginTransaction();
        u.setAccess(u.getAccess()+1);
        Datastore.put(u);
        tx.commit();
        System.out.println(u.getAccess());
        
        requestScope("url",u.getGameURL());   
        requestScope("Contents",u.getContents());
        requestScope("Operations",u.getOperations());
    
        if(u.getGameURL().isEmpty()){
            requestScope("play","unityObject.embedUnity('unityPlayer','/unitygames/GameData?key="+key+"', 600, 450);");
            
        }else{
          requestScope("play","unityObject.embedUnity('unityPlayer','"+u.getGameURL()+"', 600, 450);");
        }
     
        
        
        return forward("Game.jsp");
    }
}
