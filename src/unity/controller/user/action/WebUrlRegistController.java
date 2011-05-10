package unity.controller.user.action;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.UserMeta;
import unity.model.User;

public class WebUrlRegistController extends Controller {
    @Override
    public Navigation run() throws Exception {


        String webUrl = asString("webUrl");
        long id = asLong("userId");

        System.out.println("id:" + id);
        User user =
            Datastore
                .query(User.class)
                .filter(UserMeta.get().userId.equal(id))
                .asSingle();

        System.out.println("mid:" + user.getUserId());
        user.setWebUrl(webUrl);

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(user);
        tx.commit();

        return null;
    }
}