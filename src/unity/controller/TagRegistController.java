package unity.controller;

import java.net.URLDecoder;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.sun.org.apache.bcel.internal.generic.NEW;

import unity.meta.GameDataMeta;
import unity.meta.TagGameMeta;
import unity.meta.TagMeta;
import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.TagService;

public class TagRegistController extends Controller {
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        String ta = requestScope("a");
        String tagR = ta.substring(4);
        tagR = URLDecoder.decode(tagR, "UTF-8");
        System.out.println("たぐ" + tagR);

        if (!tagR.isEmpty()) {

            String Ga = requestScope("b");

            String GameKeyy = Ga.substring(8);
     
            Key key = KeyFactory.stringToKey(GameKeyy);

            System.out.println("きい" + key);

            GameData g = Datastore.get(GameData.class, key);
            // g.getTags().clear();
            // List<Key> tgs =
            // Datastore
            // .query(TagGame.class)
            // .filter(TagGameMeta.get().gameRef.equal(g.getKey()))
            // .asKeyList();
            // for (Key key2 : tgs) {
            // Transaction tx = Datastore.beginTransaction();
            // Datastore.delete(key2);
            // tx.commit();
            // }
            Tag tag2 = ts.getTag(tagR);
            g.getTags().add(tag2);
            Datastore.put(g);
System.out.println("aa:"+g.getTags());
            TagGame tt = new TagGame();
            tt.getGameRef().setKey(g.getKey());
            tt.getTagRef().setModel(tag2);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
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
