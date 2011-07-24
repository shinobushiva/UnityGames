package unity.controller.cron;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.TweetMeta;
import unity.meta.UserMeta;
import unity.model.Tweet;
import unity.model.User;

public class TwitterSearchController extends Controller {

    private static final String SEARCH_QUERY =
        "http://202.16.132.23/unitygames/search.php"
            + "?url=http://search.twitter.com/search.json&q=UnityGames&rpp=20&page=1";

    @SuppressWarnings("unchecked")
    @Override
    public Navigation run() throws Exception {
        String searchedResult = null;

        try {
            URL url = new URL(SEARCH_QUERY);
            URLConnection con = url.openConnection();
            con.setReadTimeout(30 * 1000);
            con.setConnectTimeout(30 * 1000);
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(
                    con.getInputStream(),
                    "utf-8"));
            String line;
            StringBuffer sb = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            searchedResult = sb.toString();

            Map<String, Object> map =
                (Map<String, Object>) JSON.decode(searchedResult);
            List<Map<String, ?>> list =
                (List<Map<String, ?>>) map.get("results");
            for (Map<String, ?> map2 : list) {
                String tweetIdString = "" + map2.get("id_str"); // tweetId
                String tweet = "" + map2.get("text"); // tweet内容
                long tweetId = Long.valueOf(tweetIdString); // long型に変換

                // userNameからuser_idを拾ってくる（from_user_idは違う。本人idではない）
                String idGet = null;
                URL ur =
                    new URL("http://202.16.132.23/unitygames/search.php"
                        + "?url=http://api.twitter.com/1/statuses/show/"
                        + tweetId
                        + ".json");
                URLConnection co = ur.openConnection();
                BufferedReader reade =
                    new BufferedReader(new InputStreamReader(
                        co.getInputStream(),
                        "utf-8"));
                String lin;
                StringBuffer s = new StringBuffer();

                while ((lin = reade.readLine()) != null) {
                    s.append(lin);
                }
                reade.close();

                idGet = s.toString();

                Map<String, Object> ma =
                    (Map<String, Object>) JSON.decode(idGet);

                String userIdString =
                    "" + ((Map<String, Object>) ma.get("user")).get("id_str"); // tweetId
                long userId = Long.valueOf(userIdString); // long型に変換
                // #UnityGamesが含まれていたら登録
                if (tweet.contains("#UnityGames")) {

                    // TweetIdが既に保存されているかチェック
                    Tweet check =
                        Datastore
                            .query(Tweet.class)
                            .filter(TweetMeta.get().tweetId.equal(tweetId))
                            .asSingle();
                    // TweetIdが保存されていなかったら
                    if (check == null) {

                        Tweet f = new Tweet();
                        f.setUserId(userId);
                        f.setTweetId(tweetId);
                        f.setText(tweet);

                        GlobalTransaction tx =
                            Datastore.beginGlobalTransaction();
                        tx.put(f);
                        tx.commit();

                        // ここでさらにUserモデルにこのつぶやきを保存するようにする

                        User userCheck =
                            Datastore
                                .query(User.class)
                                .filter(UserMeta.get().userId.equal(userId))
                                .asSingle();
                        // UserモデルのuserIdに一致する人がいたら
                        if (userCheck != null) {
                            // UserモデルにTweetを追加
                            userCheck.getTweets().add(f);

                            GlobalTransaction txx =
                                Datastore.beginGlobalTransaction();
                            txx.put(userCheck);
                            txx.commit();
                        }

                    }
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
