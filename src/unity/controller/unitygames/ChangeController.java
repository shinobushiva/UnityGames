package unity.controller.unitygames;

import java.net.URL;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import unity.meta.UserMeta;
import unity.model.GameData;
import unity.model.Tag;
import unity.model.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ChangeController extends Controller {

    @Override
    public Navigation run() throws Exception {

        String k = requestScope("key");
        String pass = requestScope("Pass");

        Key key = KeyFactory.stringToKey(k);

        GameData g = Datastore.get(GameData.class, key);

        // Passが違ったら変更画面へいかせない
        if (!g.getPass().equals(pass)) {
            return null;
        }

        requestScope("g", g);

        // 固定タグ。複数のを","で１つに
        if (g.getFixTags() != null) {
            StringBuilder buf = new StringBuilder();
            for (Tag t : g.getFixTags()) {
                buf.insert(0, t.getName() + ",");
                buf.deleteCharAt(buf.length() - 1);

                requestScope("tag", buf.toString());
            }
            // TwitterUserKeyからアカウントを取得
            if (g.getTwitterUserKey() != null) {

                User u =
                    Datastore
                        .query(User.class)
                        .filter(UserMeta.get().key.equal(g.getTwitterUserKey()))
                        .asSingle();

                Twitter twitter = new TwitterFactory().getInstance();

                twitter4j.User showUser = twitter.showUser(u.getUserId());

                String url = showUser.getProfileImageURL().toString();

                String picture = url.replace("normal", "mini");

                requestScope("userName", u.getUserName());
                requestScope("p", picture);
            }

        }
        return forward("change.jsp");
    }
}
