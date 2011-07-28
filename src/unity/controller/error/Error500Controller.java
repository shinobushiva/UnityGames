package unity.controller.error;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.SearchService;

public class Error500Controller extends Controller {

    private SearchService ss = new SearchService();

    @Override
    public Navigation run() throws Exception {

        String errorMessage = "サーバー側のエラーが発生しました。しばらくたってからもう一度アクセスしてください";

        requestScope("error", errorMessage);
        // 補完ワード
        requestScope("words", ss.suggestionWords());
        // ログイン
            requestScope("isLogin", (Boolean) sessionScope("isLogin"));
            requestScope("twitter", sessionScope("twitter"));
        return forward("500.jsp");
    }
}
