package unity.controller.admin;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import com.google.appengine.api.datastore.Key;

import unity.model.GameData;
import unity.model.User;

public class UpDateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Key createKey = Datastore.createKey(User.class, 41001l);

        Long[] ss =
            new Long[] {
                422001l,
                402301l,
                344651l,
                324201l,
                268552l,
                268551l,
                270251l };

        for (Long id : ss) {
            GameData g =
                Datastore.get(
                    GameData.class,
                    Datastore.createKey(GameData.class, id));

            g.setTwitterUserKey(createKey);

            GlobalTransaction transaction = Datastore.beginGlobalTransaction();
            Datastore.put(g);
            transaction.commit();
        }

        return null;
    }
}