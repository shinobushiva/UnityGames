package unity.controller.admin;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.LogText;

public class LogViewerController extends Controller {

    @Override
    protected Navigation run() throws Exception {

        List<LogText> asList = Datastore.query(LogText.class).asList();
        requestScope("logText", asList);

        return forward("logViewer.jsp");
    }
}
