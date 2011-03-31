package unity.controller.unitygames;


import java.net.URI;


import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;


import unity.meta.GameDataMeta;
import unity.model.GameData;


public class GameController extends Controller {

   private GameDataMeta dd = GameDataMeta.get();
    @Override
    public Navigation run() throws Exception {
       
        //どっからきたかを確認してアクセス数を増やさないようにしたい（分からないので保留）
        StringBuffer path = request.getRequestURL();
        path.append("?").append(request.getQueryString());
//        String path = new URI(request.getHeader("referrer")).getPath();
       
        System.out.println(path);
        long id = asLong("id");
     
        GameData g = Datastore.get(GameData.class, KeyFactory.createKey(dd.getKind(), id));

        System.out.println(g.getKey());
       
       
       
       

        System.out.println(id);      
        requestScope("key", g.getKey());
        
        
        
        
        
        Transaction tx = Datastore.beginTransaction();
        g.setAccess(g.getAccess()+1);
        Datastore.put(g);
        tx.commit();
        System.out.println(g.getAccess());
        
        requestScope("g",g);   
        
    
        if(g.getGameURL().isEmpty()){
            requestScope("play","unityObject.embedUnity('unityPlayer','/unitygames/GameData?id="+g.getKey().getId()+"', 600, 450);");
            
        }else{
          requestScope("play","unityObject.embedUnity('unityPlayer','"+g.getGameURL()+"', 600, 450);");
        }
     
        
        
        return forward("Game.jsp");
    }
}
