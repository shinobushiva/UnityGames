package unity.controller.ajax;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;

import com.google.appengine.api.datastore.KeyFactory;

public class GameLoadController extends Controller {
    private GameDataMeta dd = GameDataMeta.get();
    @Override
    public Navigation run() throws Exception {
        
      
//        String co = requestScope("a");
//        System.out.println("xxx:"+co);
        
        long id = asLong("id");
        
        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));
        
        System.out.println(g.getGameURL());
        
        
        if(g.getGameURL().isEmpty()){
            requestScope("play","unityObject.embedUnity('unityPlayer','/unitygames/GameData?id="+g.getKey().getId()+"', 600, 450);");
           
            }else{
            requestScope("play","unityObject.embedUnity('unityPlayer','"+g.getGameURL()+"', 600, 450);");
            }
        
        
        return forward("gameLoad.jsp");
    }
}
