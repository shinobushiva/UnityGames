package unity.controller.admin;

import java.util.HashSet;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.model.Tag;

public class UpDateController extends Controller {

    // private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        GameData g = Datastore.get(GameData.class,Datastore.createKey(GameData.class,147601));
        
        g.getTags().clear();
        g.setTags(new HashSet<Tag>());
        save(g);
        
        return null;
    }

    public void save(Object model) {

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(model);
        tx.commit();
    }

}
