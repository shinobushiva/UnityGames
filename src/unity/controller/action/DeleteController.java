package unity.controller.action;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.DeleteService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DeleteController extends Controller {

    private DeleteService service = new DeleteService();

    @Override
    public Navigation run() throws Exception {

        Key key = KeyFactory.stringToKey((String) requestScope("gameKey"));

        service.delete(key);
        service.deleteApi(key);

        return redirect("/");
    }
}
