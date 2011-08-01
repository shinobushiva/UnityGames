package unity.controller.error;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.service.SearchService;

public class BrowserErrorController extends Controller {

    private SearchService ss = new SearchService();

    @Override
    public Navigation run() throws Exception {

        String errorMessage = "お使いのブラウザでは快適にUnityGamesを使用することができません。¥n";
        errorMessage += "バージョンを最新にするか、推奨するChromeをインストールしてお楽しみください";
        requestScope("error", errorMessage);
        // 補完ワード
        requestScope("words", ss.suggestionWords());
        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));

        return forward("500.jsp");
    }
}
