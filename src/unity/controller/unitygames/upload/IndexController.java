package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import twitter4j.Twitter;
import unity.model.GameData;
import unity.model.Tag;
import unity.service.GameDataService;

public class IndexController extends Controller {
    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {

        String loginType = "guest";
        if (sessionScope("loginType") != null)
            loginType = (String) sessionScope("loginType");

        requestScope("loginType", loginType);

        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        Twitter twitter = sessionScope("twitter");
        requestScope("twitter", twitter);

        String game = asString("g");
        if (!game.equals("newGame")) {
            long id = 0;
            if (!asString("t").isEmpty() || asString("t") != null) {
                id =
                    Long.valueOf(asString("t").replace("ug", "").replace(
                        "guest",
                        ""));
            }
            System.out.println();
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
                    return redirect(request.getHeader("REFERER"));
                }
            }
            requestScope("g", g);
            System.out.println("f:" + g);
            // 固定タグ。複数のを","で１つに
            if (g.getFixTags() != null && !g.getFixTags().isEmpty()) {
                StringBuilder buf = new StringBuilder();
                for (Tag t : g.getFixTags()) {
                    buf.insert(0, t.getName() + ",");
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                requestScope("tag", buf.toString());
            }
            String[] size = g.getGameScreenSize().split(",");
            requestScope("width", size[0]);
            requestScope("height", size[1]);
            return forward("change.jsp");
        } else {
            return forward("index.jsp");
        }

    }
}
