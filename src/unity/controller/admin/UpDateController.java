package unity.controller.admin;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

public class UpDateController extends Controller {

    // private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        return null;
    }

    public void save(Object model) {

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(model);
        tx.commit();
    }

}
