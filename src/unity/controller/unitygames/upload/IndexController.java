package unity.controller.unitygames.upload;

import java.util.UUID;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import twitter4j.Twitter;
import unity.model.GameData;
import unity.model.api.SaveLoadId;
import unity.service.GameDataService;
import unity.service.SaveDataService;
import unity.service.SearchService;
import unity.service.UploadService;
import unity.service.UserService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class IndexController extends Controller {
    private GameDataService gs = new GameDataService();
    private SaveDataService sd = new SaveDataService();
    private UserService us = new UserService();
    private UploadService up = new UploadService();
    private SearchService ss = new SearchService();
    @Override
    public Navigation run() throws Exception {
        String loginType = "guest";

        // 補完ワード
        requestScope("words", ss.suggestionWords());

        if (sessionScope("loginType") != null)
            loginType = (String) sessionScope("loginType");
        removeSessionScope("loginType");
        requestScope("loginType", loginType);
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        Twitter twitter = (Twitter) sessionScope("twitter");
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

            String k = requestScope("key");
            String pass = requestScope("Pass");

            GameData g = null;
            if (k != null) {
                Key key = KeyFactory.stringToKey(k);
                g = Datastore.get(GameData.class, key);
            }
            if (id != 0) {
                g = gs.load(id);
                if (g.getTwitterUserKey() != null) {
                    Long uId = us.getUser(g.getTwitterUserKey()).getUserId();
                    if (!uId.equals(twitter.getId()))
                        return redirect(request.getHeader("REFERER"));
                }
            }
            // Passが違ったら変更画面へいかせない
            if (k != null) {
                if (!g.getPass().equals(pass) && id == g.getKey().getId()) {
                    return redirect(request.getHeader("REFERER"));
                }
            }
            requestScope("g", g);
            // 固定タグ。複数のを","で１つに
            if (g.getFixTags() != null && !g.getFixTags().isEmpty()) {
                requestScope("tag", gs.connectFixTags(g));
            }

            String[] size = g.getGameScreenSize().split(",");
            requestScope("width", size[0]);
            requestScope("height", size[1]);

            // save,laod id
            requestScope("sl", sd.getSaveLoadId(g.getKey()));

            try {
                SaveLoadId saveLoadId =
                    Datastore.get(SaveLoadId.class, g.getKey());
                if (saveLoadId == null
                    || saveLoadId.getLoadId() == null
                    || saveLoadId.getSaveId() == null)
                    up.saveLoadId(
                        g.getKey(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return forward("change.jsp");
        } else {
            // create save,load id
            requestScope("saveId", UUID.randomUUID().toString());
            requestScope("loadId", UUID.randomUUID().toString());
            return forward("index.jsp");
        }

    }
}
