package unity.controller.ajax;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;
import unity.model.Tag;

import com.google.appengine.api.datastore.KeyFactory;

public class TagUploadController extends Controller {

    private GameDataMeta dd = GameDataMeta.get();

    @Override
    public Navigation run() throws Exception {

        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));
requestScope("g",g);
        
//        StringBuilder buf = new StringBuilder();
//        for (Tag t : g.getTags()) {
//            buf.insert(0, t.getName());
//            System.out.println("buf:"+buf);
//
//        }
//        buf.deleteCharAt(buf.length() - 1);
        
//        Tag tag = Datastore.query(Tag.class, g.getKey()).asSingle();
//
//        String str = tag.getTag();
//        String[] Tag = str.split(",");
//        requestScope("tag", Tag);

        return forward("tagUpload.jsp");
    }
}
