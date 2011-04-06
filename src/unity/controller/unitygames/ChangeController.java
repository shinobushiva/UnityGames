package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.TagMeta;
import unity.model.GameData;
import unity.model.Tag;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ChangeController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
       String k = requestScope("key");
       String pass = requestScope("Pass");
      
       Key key = KeyFactory.stringToKey(k);
       
       GameData g = Datastore.get(GameData.class, key);
       
       if(!g.getPass().equals(pass)){
           return redirect("index");
       }
       
       requestScope("g",g);
       requestScope("ttt", g.getThumbNailURL());
       
       Tag tag = Datastore.query(Tag.class,g.getKey()).asSingle();
       
       requestScope("tag", tag);
     
        
        return forward("change.jsp");
    }
}
