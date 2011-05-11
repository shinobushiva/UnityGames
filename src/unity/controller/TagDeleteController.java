package unity.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.TagService;

import com.google.appengine.api.datastore.KeyFactory;

public class TagDeleteController extends Controller {
    // private GameDataMeta dd = GameDataMeta.get();
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        Long tagId = asLong("tagId");
        Tag tag =
            Datastore.get(Tag.class, Datastore.createKey(Tag.class, tagId));

        String gameKey = asString("gameKey");
        GameData g =
            Datastore.get(GameData.class, KeyFactory.stringToKey(gameKey));

        // System.out.println("deleteたぐ" + tagD);
        // System.out.println("きい" + gameKeyy);
        // System.out.println(key);
        // System.out.println(g.getTags());
        TagGame tg = ts.getTagGame(g.getKey(), tag.getKey());
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.delete(tg.getKey());
        tx.commit();

        g.getTags().remove(tag);
        tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.commit();
        // System.out.println("bbb:" + g.getTags());
        // requestScope("g", g);

        return null;
    }
}
