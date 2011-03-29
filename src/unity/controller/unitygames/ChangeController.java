package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.GameData;

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
     
       if(g.getThumbNailURL().length() >40){
           int a = g.getThumbNailURL().length() / 40;
           String l = "";
           for(int i=0;i<a;i++ ){
                l = l + g.getThumbNailURL().substring(i*40,40+40*i)+"<br>";
           }
           String b = l + g.getThumbNailURL().substring(40*a+1);
           g.setThumbNailURL(b);
           }
       
       if(g.getGameURL().length() >40){
       int a = g.getGameURL().length() / 40;
       String l = "";
       for(int i=0;i<a;i++ ){
            l = l + g.getGameURL().substring(i*40,40+40*i)+"<br>";
       }
       String b = l + g.getGameURL().substring(40*a+1);
       g.setGameURL(b);
       }
       if(g.getHpURL().length() >40){
           int a = g.getHpURL().length() / 40;
           
           String l = "";
           for(int i=0;i<a;i++ ){
                l = l + g.getHpURL().substring(i*40,40+40*i)+"<br>";
           }
           String b = l + g.getHpURL().substring(40*a+1);
           g.setHpURL(b);
           }
//       String l = g.getGameURL().substring(0,40)+"<br>"+g.getGameURL().substring(40);
//       System.out.println(l);
//       System.out.println(g.getGameURL());
//           long l = g.getDate().getTime() + 1000 * 60 * 60 * 9;
//           g.getDate().setTime(l);
     
        
        return forward("change.jsp");
    }
}
