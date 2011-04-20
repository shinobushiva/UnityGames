package unity.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.TagGameMeta;
import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.TagService;

public class TutorialController extends Controller {
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        String tags = "チュートリアル";

        Set<TagGame> tgl = new HashSet<TagGame>();
            Tag tag2 = ts.getTag(tags);
            List<TagGame> tgs =
                Datastore
                    .query(TagGame.class)
                    .filter(TagGameMeta.get().tagRef.equal(tag2.getKey()))
                    .asList();
            tgl.addAll(tgs);

        Set<GameData> gds = new HashSet<GameData>();
        for (TagGame gameData : tgl) {

            gds.add(gameData.getGameRef().getModel());

        }
System.out.println("gds:"+gds);
       
        requestScope("tutorial", gds);
        for (GameData game : gds) {
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

            // Tag t = Datastore.query(Tag.class,game.get).asSingle();
            // System.out.println("ttt:"+t);

        }
        return forward("tutorial.jsp");
    }
}
