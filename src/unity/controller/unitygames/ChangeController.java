package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import twitter4j.Twitter;
import unity.meta.UserMeta;
import unity.model.GameData;
import unity.model.Tag;
import unity.model.User;
import unity.service.GameDataService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ChangeController extends Controller {

    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {
        // Twitter twitter = (Twitter) sessionScope("twitter");
        long id = 0;
        if (asLong("id") != null) {
            id = asLong("id");
        }
        String k = requestScope("key");
        String pass = requestScope("Pass");

        GameData g = null;
        if (k != null) {

            Key key = KeyFactory.stringToKey(k);
            g = Datastore.get(GameData.class, key);
        }
        if (id != 0) {
            g = gs.load(id);
        }
        // Passが違ったら変更画面へいかせない
        if (k != null) {
            if (!g.getPass().equals(pass) && id == 0) {
                return null;
            }
        }
        requestScope("g", g);

        // 固定タグ。複数のを","で１つに
        if (g.getFixTags() != null) {
            StringBuilder buf = new StringBuilder();
            for (Tag t : g.getFixTags()) {
                buf.insert(0, t.getName() + ",");
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            requestScope("tag", buf.toString());
            // TwitterUserKeyからアカウントを取得
            if (g.getTwitterUserKey() != null) {

            }

        }
        String[] size = g.getGameScreenSize().split(",");
        requestScope("width", size[0]);
        requestScope("height", size[1]);
        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        Twitter twitter = sessionScope("twitter");
        requestScope("twitter", twitter);
        String type = "";
        if (twitter == null)
            type = "guest";
        else
            type = "twitter";

        requestScope("type", type);

        return forward("change.jsp");
    }
}
