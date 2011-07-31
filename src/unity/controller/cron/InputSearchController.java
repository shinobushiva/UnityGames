package unity.controller.cron;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.TagService;

public class InputSearchController extends Controller {
    private TagService ts = new TagService();

    @Override
    protected Navigation run() throws Exception {
        ts.tagCleaner();
        ts.inputSearch();
        return null;
    }
}
