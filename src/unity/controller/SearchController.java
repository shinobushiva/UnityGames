package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.GameData;
import unity.service.SearchService;

public class SearchController extends Controller {
    private SearchService ss = new SearchService();

    @Override
    public Navigation run() throws Exception {

        String keyWord = "";
        String type = "";
        List<GameData> gds = null;
        if ("word".equals(asString("type"))) {
            gds = ss.searchWord(asString("q"));
            keyWord = asString("word");
            type = "word";
        }

        if ("tag".equals(asString("type"))) {
            gds = ss.searchTag(asString("q"));
            keyWord = asString("tag");
            type = "tag";
        }

        requestScope("word", keyWord);
        requestScope("type", type);

        requestScope("GameList", gds);
        // 補完ワード
        requestScope("words", ss.suggestionWords());
        requestScope("tags", ss.suggestionTags());
        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));
        return forward("search.jsp");

    }
}
