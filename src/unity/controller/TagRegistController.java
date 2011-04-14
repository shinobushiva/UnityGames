package unity.controller;

import java.net.URLDecoder;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import unity.meta.GameDataMeta;
import unity.meta.TagMeta;
import unity.model.GameData;
import unity.model.Tag;

public class TagRegistController extends Controller {
    private GameDataMeta dd = GameDataMeta.get();

    @Override
    public Navigation run() throws Exception {

        String ta = requestScope("a");
        String Ga = requestScope("b");

        String tagR = ta.substring(4);
        tagR = URLDecoder.decode(tagR, "UTF-8");

        String GameKeyy = Ga.substring(8);
        System.out.println("たぐ" + tagR);
        System.out.println("きい" + GameKeyy);
        Key key = KeyFactory.stringToKey(GameKeyy);

        System.out.println(key);
        System.out.println(tagR);

        if (!tagR.isEmpty()) {
            String tagReg = tagR + ",";
            System.out.println(tagReg);
            Tag tag = Datastore.query(Tag.class, key).asSingle();
            String tagRegist = tag.getTag() + tagReg;
            tag.setTag(tagRegist);
            Transaction tx = Datastore.beginTransaction();
            Datastore.put(tag);
            tx.commit();
        }
        return null;
    }
}
