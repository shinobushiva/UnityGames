package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;

public class UpDateController extends Controller {

    @Override
    public Navigation run() throws Exception {

     List<GameData> asList = Datastore.query(GameData.class).asList();
     
     for (GameData g : asList) {
         int point = g.getAccess() + g.getComment() * 3;
         g.setPoint(point);
         g.setTwitterUserKey(null);
        
         GlobalTransaction tx = Datastore.beginGlobalTransaction();
         tx.put(g);
         tx.commit();
         
    }
        
        

        return null;
    }
}
