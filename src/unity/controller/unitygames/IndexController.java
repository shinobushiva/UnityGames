package unity.controller.unitygames;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;

public class IndexController extends Controller {

    private GameDataMeta g = GameDataMeta.get();

    // private NoteMeta n = NoteMeta.get();

    @Override
    public Navigation run() throws Exception {
//ゲーム新着順
        List<GameData> games = Datastore.query(g).sort(g.access.desc).asList();

        requestScope("GameList", games);
        for (GameData game : games) {
            long l = game.getDate().getTime() + 1000 * 60 * 60 * 9;
            game.getDate().setTime(l);

            if (game.getContents().length() >= 80) {
                String s = game.getContents().substring(0, 80);
                game.setContents(s + "...");
            }
            if (game.getOperations().length() >= 80) {
                String o = game.getOperations().substring(0, 80);
                game.setOperations(o + "...");
            }

        }
      //ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));

        return forward("index.jsp");
    }
}
