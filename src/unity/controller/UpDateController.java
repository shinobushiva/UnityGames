package unity.controller;

import java.util.HashSet;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.Tweet;
import unity.model.User;

public class UpDateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        
        User uuu = new User();
        uuu.setUserName("kyusyukeigo");
        uuu.setUserId(163412860);
        uuu.setWebUrl("http://unitygames/");
        uuu.setTweets(new HashSet<Tweet>());
        GlobalTransaction ttt = Datastore.beginGlobalTransaction();
        Datastore.put(uuu);
  ttt.commit();
      

        return null;
    }
}
