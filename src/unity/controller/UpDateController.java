package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.TagService;

public class UpDateController extends Controller {

    private TagService service = new TagService();

    @Override
    public Navigation run() throws Exception {

        List<Tag> asList = Datastore.query(Tag.class).asList();
        for (Tag t : asList) {

//            String tag = t.getTag();
            String ft = t.getFixTag();
//            String[] Tag = tag.split(",");
//            for (String string : Tag) {
//
//                service.getTag(string.trim());
//
//            }
            String[] fixTag = ft.split(",");
            for (String string : fixTag) {

                service.getTag(string.trim());

            }

        }

        List<GameData> g = Datastore.query(GameData.class).asList();

        for (GameData gd : g) {

            Tag t = Datastore.get(Tag.class, gd.getKey().getParent());

            String tag = t.getTag();
            String ft = t.getFixTag();
            String[] tags = tag.split(",");
            String[] fixTags = ft.split(",");
            for (String ts : tags) {

                Tag tag2 = service.getTag(ts);
                gd.getTags().add(tag2);

                TagGame tg = new TagGame();

                tg.getGameRef().setModel(gd);
                tg.getTagRef().setKey(tag2.getKey());
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                Datastore.put(tg);
                tx.commit();

            }
            for (String ts : fixTags) {

                Tag tag2 = service.getTag(ts);
                gd.getFixTags().add(tag2);
                TagGame tg = new TagGame();

                tg.getGameRef().setModel(gd);
                tg.getTagRef().setKey(tag2.getKey());
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                Datastore.put(tg);
                tx.commit();

            }
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            Datastore.put(gd);
            tx.commit();

        }

        return null;
    }
}
