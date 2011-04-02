package unity.controller;



import java.net.URLDecoder;
import java.util.Date;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.Comment;
import unity.model.GameData;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class CommentUpController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String co = requestScope("a");
        String Ga = requestScope("b");
        
        String com = co.substring(8);
        com = URLDecoder.decode(com, "UTF-8");

        String GameKeyy = Ga.substring(8);
        System.out.println("コメント："+com);
        System.out.println("きい"+GameKeyy);
        Key key = KeyFactory.stringToKey(GameKeyy);
       
        System.out.println(key);
        if(!com.isEmpty()){
        Comment comment = new Comment();
        comment.setComment(com);
        comment.setDate(new Date());
        comment.setGameDataKey(key);
        GameData g = Datastore.get(GameData.class,key);
        g.setComment(g.getComment()+1);
        Datastore.put(g);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(comment);
        tx.commit();
        }
        return null;
    }
}
