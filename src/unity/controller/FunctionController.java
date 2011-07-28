package unity.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.SearchService;

public class FunctionController extends Controller {
    private SearchService ss = new SearchService();
    @Override
    public Navigation run() throws Exception {
        
        // 補完ワード
        requestScope("words", ss.suggestionWords());
      //ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));
        return forward("function.jsp");
    }
}
