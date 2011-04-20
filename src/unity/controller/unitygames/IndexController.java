package unity.controller.unitygames;

import java.util.List;

import javax.xml.crypto.Data;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.meta.NoteMeta;
import unity.meta.TagMeta;

import unity.model.Note;
import unity.model.GameData;
import unity.model.Tag;

public class IndexController extends Controller {

    private GameDataMeta g = GameDataMeta.get();

    // private NoteMeta n = NoteMeta.get();

    @Override
    public Navigation run() throws Exception {

        List<GameData> Game = Datastore.query(g).sort(g.access.desc).asList();

        requestScope("GameList", Game);
        for (GameData game : Game) {
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

        return forward("index.jsp");
    }
}
