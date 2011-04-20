package unity.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.crypto.Data;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.GameDataMeta;
import unity.meta.TagGameMeta;
import unity.meta.TagMeta;
import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.service.TagService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TagDeleteController extends Controller {
    private GameDataMeta dd = GameDataMeta.get();
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        String ta = requestScope("a");
        String Ga = requestScope("b");

        String tagD = ta.substring(7);
        tagD = URLDecoder.decode(tagD, "UTF-8");
        String GameKeyy = Ga.substring(8);
        System.out.println("deleteたぐ" + tagD);
        System.out.println("きい" + GameKeyy);
        Key key = KeyFactory.stringToKey(GameKeyy);
        System.out.println(key);

        GameData g = Datastore.get(GameData.class, key);
        Tag tag = ts.getTag(tagD);
        TagGame tg = ts.getTagGame(key, tag.getKey());
        g.getTags().remove(tag);
        System.out.println(g.getTags());
        GlobalTransaction x = Datastore.beginGlobalTransaction();
        Datastore.delete(tg.getKey());
        x.commit();
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(g);
        tx.commit();
        System.out.println("bbb:" + g.getTags());
        requestScope("g", g);

        return null;
    }
}
