package unity.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.meta.TagMeta;
import unity.model.GameData;
import unity.model.Tag;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class TagDeleteController extends Controller {
    private GameDataMeta dd = GameDataMeta.get();
    @Override
    public Navigation run() throws Exception {
        
        String ta = requestScope("a");
        String Ga = requestScope("b");
        
        String tagD = ta.substring(7);
        tagD = URLDecoder.decode(tagD, "UTF-8");
        String GameKeyy = Ga.substring(8);
        System.out.println("deleteたぐ"+tagD);
        System.out.println("きい"+GameKeyy);
        Key key = KeyFactory.stringToKey(GameKeyy);
        System.out.println(key);
        Tag tag = Datastore.query(Tag.class,key).asSingle();
        
        
        String str = tag.getTag();
        String[] Tag = str.split(",");
        List<String> list = new ArrayList<String>(Arrays.asList(Tag));
        Transaction tx = Datastore.beginTransaction();
      list.remove(tagD);
      String get= "";
      for (String l : list){
          
           get = get + l + ","; 
            
      }
      System.out.println("げｔ："+get);
      
      //        tag.setTag(buf.toString());
//        System.out.println("ばふ"+buf.toString());
       
//            if (buf.length()>0) {
//                buf.append(with);
//            }
//            buf.append(s);
        tag.setTag(get);
        Datastore.put(tag);
        tx.commit();
       
        GameData g = Datastore.get(GameData.class,key);
        requestScope("g",g);
        System.out.println("ぎいいい"+g.getKey());
        
        return null;
    }
}
