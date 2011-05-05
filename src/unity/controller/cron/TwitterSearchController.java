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

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import unity.meta.TweetMeta;
import unity.meta.UserMeta;
import unity.model.Tweet;
import unity.model.User;

public class TwitterSearchController extends Controller {

    private static final String SEARCH_QUERY =
        "http://202.16.132.23/unitygames/search.php"
            + "?url=http://search.twitter.com/search.json&q=UnityGames&rpp=20&lang=ja&page=1";

    @SuppressWarnings("unchecked")
    @Override
    public Navigation run() throws Exception {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb
            .setOAuthAccessToken(
                "276331612-GGKg4ShbCjuAcTnZT4nElDSS5nx1U9PPktAt8dmw")
            .setOAuthAccessTokenSecret(
                "Tl0qGUzkyA664GoQbOi3Uj5vgGMbBd92yL6RISSPNo");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        String searchedResult = null;
        // InputStream is = null;

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
            // System.out.println(searchedResult);

            Map<String, Object> map =
                (Map<String, Object>) JSON.decode(searchedResult);
            List<Map<String, ?>> list =
                (List<Map<String, ?>>) map.get("results");
            for (Map<String, ?> map2 : list) {
                String tweetIdString = "" + map2.get("id_str"); // tweetId
                String lang = "" + map2.get("iso_language_code"); // 言語
                String userName = "" + map2.get("from_user"); // 誰が呟いたか
                String tweet = "" + map2.get("text"); // tweet内容
                long tweetId = Long.valueOf(tweetIdString); // long型に変換

                // userNameからuser_idを拾ってくる（from_user_idは違う。本人idではない）
                String idGet = null;
                URL ur =
                    new URL("http://twitter.com/users/show/"
                        + userName
                        + ".json");
                URLConnection co = ur.openConnection();
                // co.setReadTimeout(30 * 1000);
                // co.setConnectTimeout(30 * 1000);
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

                String userIdString = "" + ma.get("id"); // tweetId
                long userId = Long.valueOf(userIdString); // long型に変換
System.out.println(tweet);
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
                      
                        GlobalTransaction tx =
                            Datastore.beginGlobalTransaction();
                        Datastore.put(f);
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
                            Datastore.put(userCheck);
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
