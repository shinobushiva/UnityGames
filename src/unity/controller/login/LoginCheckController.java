package unity.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.SearchService;

public class LoginCheckController extends Controller {
    private SearchService ss = new SearchService();
    @Override
    public Navigation run() throws Exception {

     // 補完ワード
        requestScope("words", ss.suggestionWords());
        requestScope("tags", ss.suggestionTags());
        return forward("loginCheck.jsp");
    }
}
