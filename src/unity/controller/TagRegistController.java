package unity.controller;

import java.net.URLDecoder;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.TagService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TagRegistController extends Controller {
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        String tagR = asString("tag");
        tagR = URLDecoder.decode(tagR, "UTF-8");

        if (!tagR.isEmpty()) {

            String GameKeyy = asString("gameKey");

            Key key = KeyFactory.stringToKey(GameKeyy);

            System.out.println("きい" + key);

            GameData g = Datastore.get(GameData.class, key);
            Tag tag2 = ts.getTag(tagR);
            g.getTags().add(tag2);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            Datastore.put(g);
            tx.commit();

            System.out.println("aa:" + g.getTags());
            TagGame tt = ts.getTagGame(g.getKey(), tag2.getKey());
            if (tt == null) {
                tt = new TagGame();
                tt.getGameRef().setKey(g.getKey());
                tt.getTagRef().setModel(tag2);
            }
            
            tx = Datastore.beginGlobalTransaction();
            Datastore.put(tt);
            tx.commit();

        }

        // if (!tagR.isEmpty()) {
        // String tagReg = tagR + ",";
        // System.out.println(tagReg);
        // Tag tag = Datastore.query(Tag.class, key).asSingle();
        // String tagRegist = tag.getTag() + tagReg;
        // tag.setTag(tagRegist);
        // Transaction tx = Datastore.beginTransaction();
        // Datastore.put(tag);
        // tx.commit();
        // }

        return null;
    }
}
