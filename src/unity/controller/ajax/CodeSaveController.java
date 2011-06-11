package unity.controller.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.topgate.controller.JsonController;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.CommentMeta;
import unity.meta.GameDataMeta;
import unity.model.Comment;
import unity.model.GameData;
import unity.service.GameDataService;

import com.google.appengine.api.datastore.KeyFactory;

public class CodeSaveController extends JsonController {
    private GameDataMeta dd = GameDataMeta.get();
    private GameDataService gs = new GameDataService();

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        long id = asLong("id");
        String code = asString("codeEditArea");
        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));

        if (g.isEditable()) {
            return map;
        }

        g.setCode(code);
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.commit();

        map.put("code", gs.toCodeJson(code));

        return map;
    }
}