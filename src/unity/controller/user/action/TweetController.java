package unity.controller.user.action;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;

public class TweetController extends Controller {

    @Override
    public Navigation run() throws Exception {
        // protected Map<String, Object> handle() throws Exception {
        //
        // Map<String, Object> maps = new HashMap<String, Object>();
        // // ajaxで呟くようにする。

        String tweet = asString("tweet");

        boolean b = asBoolean("check");

        // 自分のアカウント
        Twitter twitter = (Twitter) sessionScope("twitter");

        if (b == false) {
            System.out.println("#UnityGamesつける");
            twitter.updateStatus(tweet + "#UnityGames");

        } else {
            System.out.println("#UnityGamesつけない");
            twitter.updateStatus(tweet);
        }

        // つぶやく。#UnityGames付きで
        //
        // // userIdからモデルを取り出す
        // unity.model.User uk =
        // Datastore
        // .query(unity.model.User.class)
        // .filter(UserMeta.get().userId.equal(twitter.getId()))
        // .asSingle();
        // // TweetIdを取り出す
        // Set<Tweet> tweets = uk.getTweets();
        //
        // // とってきたTweetをリスト化する準備
        // List<Object> t = new ArrayList<Object>();
        // //
        // TweetIdだけのTweetを取り出す　そこでTweetIdがなかったら(削除済みだったら)Userモデルから、Tweetモデルからも削除しておく
        // for (Tweet tt : tweets) {
        //
        // String searchedResult = null;
        //
        // URL url =
        // new URL("http://twitter.com/statuses/show/"
        // + tt.getTweetId()
        // + ".json");
        // URLConnection con = url.openConnection();
        // con.setReadTimeout(30 * 1000);
        // con.setConnectTimeout(30 * 1000);
        // BufferedReader reader =
        // new BufferedReader(new InputStreamReader(
        // con.getInputStream(),
        // "utf-8"));
        // String line;
        // StringBuffer sb = new StringBuffer();
        //
        // while ((line = reader.readLine()) != null) {
        // sb.append(line);
        // }
        // reader.close();
        //
        // searchedResult = sb.toString();
        // Map<String, Object> map =
        // (Map<String, Object>) JSON.decode(searchedResult);
        //
        // // tweetなかったらnullがくる
        // String twt = "" + map.get("text");
        //
        // // TweetIdがなかったら(本家で削除済みだったら)UserモデルとTweetモデルから削除
        // if (twt == null) {
        // // Setの中の１つを削除。これであっているのか不明
        // tweets.remove(tt);
        // // Tweetから削除
        // GlobalTransaction tx = Datastore.beginGlobalTransaction();
        // Datastore.delete(tt.getKey());
        // tx.commit();
        // continue;
        // }
        // // リストに入れていく
        // t.add(tweet);
        //
        // }
        // maps.put("tweet", t);
        // return maps;
        // }
        return null;
    }
}
