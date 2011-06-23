package unity.controller;

import java.net.URLDecoder;
import java.util.HashSet;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.TagService;

import com.google.appengine.api.datastore.KeyFactory;

public class TagRegistController extends Controller {
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        String tagR = asString("tag");
        tagR = URLDecoder.decode(tagR, "UTF-8");

        if (!tagR.isEmpty()) {

            String gameKeyy = asString("gameKey");

            GameData g =
                Datastore.get(GameData.class, KeyFactory.stringToKey(gameKeyy));

            Tag tag2 = ts.getTag(tagR);

            if (g.getTags() == null) {
                g.setTags(new HashSet<Tag>());
                GlobalTransaction txt = Datastore.beginGlobalTransaction();
                txt.put(g);
                txt.commit();
            }
            g.getTags().add(tag2);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(g);
            tx.commit();
            // 二度手間してるけど力尽きたので放置・・・6/14
            ts.conflictTag(g.getKey());
            ts.setRelation(g.getKey());
            ts.deleteRelationTag(g.getKey());

            TagGame tt = ts.getTagGame(g.getKey(), tag2.getKey());
            if (tt == null) {
                tt = new TagGame();
                tt.getGameRef().setKey(g.getKey());
                tt.getTagRef().setModel(tag2);
            }

            tx = Datastore.beginGlobalTransaction();
            tx.put(tt);
            tx.commit();

        }

        return null;
    }
}
