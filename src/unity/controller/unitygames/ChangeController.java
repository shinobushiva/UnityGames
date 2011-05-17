package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import twitter4j.ProfileImage;
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
        Twitter twitter = (Twitter) sessionScope("twitter");
        long id = 0;
        if (asLong("id") != null) {
            id = asLong("id");
        }
        String k = requestScope("key");
        String pass = requestScope("Pass");

        System.out.println("kkkk?:" + k);

        GameData g = null;
        if (k != null) {

            Key key = KeyFactory.stringToKey(k);
            g = Datastore.get(GameData.class, key);
        }
        if (id != 0) {
            g =
                Datastore.get(
                    GameData.class,
                    Datastore.createKey(GameData.class, id));
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

                Twitter twitt = new TwitterFactory().getInstance();

                twitter4j.User showUser = twitt.showUser(u.getUserId());

                String url = showUser.getProfileImageURL().toString();

                String picture = url.replace("normal", "mini");

                requestScope("userName", u.getUserName());
                requestScope("p", picture);

                if (id != 0) {

                    if (twitter.getId() != u.getUserId()) {
                        return null;
                    }

                }

            }

        }

        if (twitter == null) {

            requestScope("userName", "Guest");
            requestScope("type", "guest");
            // requestScope(
            // "p",
            // "/images/face.png");

        } else {
            ProfileImage profileimage = null;
            // Twitter画像URL取得
            requestScope("userName", twitter.getScreenName());
            requestScope(
                "p",
                twitter.getProfileImage(
                    twitter.getScreenName(),
                    profileimage.MINI).getURL());
            requestScope("type", "twitter");

        }

        return forward("change.jsp");
    }
}
