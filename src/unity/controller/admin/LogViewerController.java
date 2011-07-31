package unity.controller.admin;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.LogMeta;
import unity.model.Log;

public class LogViewerController extends Controller {

    @Override
    protected Navigation run() throws Exception {

        // Integer page = asInteger("page");
        // if (page == null) {
        // page = 0;
        // }
        // int num = 100;
        // List<Log> logs =
        // Datastore.query(Log.class).offset(page *
        // num).limit(num).sort(LogMeta.get().date.desc).asList();
        //
        // requestScope("logs", logs);

        return forward("logViewer.jsp");
    }
}
