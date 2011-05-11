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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TagRegistController extends Controller {
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        String tagR = asString("tag");
        tagR = URLDecoder.decode(tagR, "UTF-8");

        if (!tagR.isEmpty()) {

            String gameKeyy = asString("gameKey");

            Key key = KeyFactory.stringToKey(gameKeyy);

            System.out.println("きい" + key);
            System.out.println("game??:" + Datastore.get(GameData.class, key));
            GameData g = Datastore.get(GameData.class, key);
            System.out.println("g" + g);
            Tag tag2 = ts.getTag(tagR);
            if (g.getTags() == null) {
                g.setTags(new HashSet<Tag>());
                GlobalTransaction txt = Datastore.beginGlobalTransaction();
                txt.put(g);
                txt.commit();
            }
            System.out.println("g.getTags:" + g.getTags());
            System.out.println("tag2:" + tag2);
            g.getTags().add(tag2);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(g);
            tx.commit();

            System.out.println("aa:" + g.getTags());
            TagGame tt = ts.getTagGame(g.getKey(), tag2.getKey());
            System.out.println("TagGame一致かどうか：" + tt);
            System.out.println("ゲームの中のタグ：" + g.getTags());
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
