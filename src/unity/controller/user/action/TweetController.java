package unity.controller.user.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.co.topgate.controller.JsonController;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import twitter4j.Status;
import twitter4j.Twitter;
import unity.meta.UserMeta;
import unity.model.Tweet;

public class TweetController extends JsonController {

    @SuppressWarnings({ "unchecked", "unused" })
    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> maps = new HashMap<String, Object>();
        // ajaxで呟くようにする。

        String tweet = asString("tweet");

        // 自分のアカウント
        Twitter twitter = (Twitter) sessionScope("twitter");
        // つぶやく。#UnityGames付きで
        twitter.updateStatus(tweet + "#UnityGames");

        // userIdからモデルを取り出す
        unity.model.User uk =
            Datastore
                .query(unity.model.User.class)
                .filter(UserMeta.get().userId.equal(twitter.getId()))
                .asSingle();
        // TweetIdを取り出す
        Set<Tweet> tweets = uk.getTweets();

        // とってきたTweetをリスト化する準備
        List<Object> t = new ArrayList<Object>();
        // TweetIdだけのTweetを取り出す　そこでTweetIdがなかったら(削除済みだったら)Userモデルから、Tweetモデルからも削除しておく
        for (Tweet tt : tweets) {

            String searchedResult = null;

            URL url =
                new URL("http://twitter.com/statuses/show/"
                    + tt.getTweetId()
                    + ".json");
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

            // tweetなかったらnullがくる
            String twt = "" + map.get("text");

            // TweetIdがなかったら(本家で削除済みだったら)UserモデルとTweetモデルから削除
            if (twt == null) {
                // Setの中の１つを削除。これであっているのか不明
                tweets.remove(tt);
                // Tweetから削除
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                Datastore.delete(tt.getKey());
                tx.commit();
                continue;
            }
            // リストに入れていく
            t.add(tweet);

        }
        maps.put("tweet", t);
        return maps;
    }
}
