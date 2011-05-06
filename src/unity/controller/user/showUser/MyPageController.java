package unity.controller.user.showUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import unity.meta.GameDataMeta;
import unity.meta.UserMeta;
import unity.model.GameData;
import unity.model.Tweet;

public class MyPageController extends Controller {

    @SuppressWarnings({ "unchecked", "unused" })
    @Override
    public Navigation run() throws Exception {

        String name = asString("name");

        // アカウント名からモデルのuserIdを取り出す
        unity.model.User uk =
            Datastore
                .query(unity.model.User.class)
                .filter(UserMeta.get().userName.equal(name))
                .asSingle();
        System.out.println("uk:" + uk);
        // userIdからイメージ画像を持ってくる
        Twitter twitter = new TwitterFactory().getInstance();
        twitter4j.User u = twitter.showUser(163412860);
        System.out.println("u:" + u);

        String imageURL = u.getProfileImageURL().toString();

        String picture = imageURL.replace("normal", "reasonably_small");

        // TweetIdを取り出す
        Set<Tweet> tweets = uk.getTweets();
        
        
        
        

        // /*毎回1つ1つjsonでStatus(Tweet)idで持ってきてそのツイートがあるかないか確認しながら行う処理*/
        // /*でもAPI１５０回制限で厳しいので保留。まとめて取得できるならこのやり方でいく*/
        // /*今のところできないので削除は考えず(削除ボタン作ればいいけど2度・・・)モデルに保存する*/
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
        // String tweet = "" + map.get("text");
        //
        // // TweetIdがなかったら(本家で削除済みだったら)UserモデルとTweetモデルから削除
        // if (tweet == null) {
        // // Setの中の１つを削除。あっていない
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

        // ちょい保留 別の方法があると思うから
        //
        // // 最新200ツイートのうち#UnityGmaesが付いているツイートを取得
        // // 保存も考えたけど毎回持ってきたほうがいいと思った。削除とかめんどい。特に利用者
        // Paging p = new Paging(1, 200);
        // ResponseList<Status> r = tw.getUserTimeline(u.getId(), p);
        //
        // // リスト作成
        // List<Object> tweet = new ArrayList<Object>();
        // for (Status t : r) {
        // // #UnityGamesタグが付いているか判断
        // if (t.getText().contains("#UnityGames")) {
        // // 追加していく
        // tweet.add(t.getText());
        // }
        //
        // }
        // ちょい保留 別の方法があると思うから

        // 登録ゲームを取得(とりあえず新着順)
        List<GameData> g =
            Datastore
                .query(GameData.class)
                .filter(GameDataMeta.get().twitterUserKey.equal(uk.getKey()))
                .sort(GameDataMeta.get().date.desc)
                .asList();

        // 登録ゲームのリスト
        requestScope("gameList", g);
        // webUrlを表示
        requestScope("webUrl", uk.getWebUrl());
        // 該当したtweetのリスト
        requestScope("tweet", tweets);
        // Twitterアカウント
        requestScope("u", u);
        // TwitterProfilePicture
        requestScope("tp", picture);

        // Twitterアカウントでとれるもの
        System.out.println("CreatedAt:" + u.getCreatedAt());
        System.out.println("Description:" + u.getDescription());
        System.out.println("FavouritesCount:" + u.getFavouritesCount());
        System.out.println("FollowersCount:" + u.getFollowersCount());
        System.out.println("FriendsCount:" + u.getFriendsCount());
        System.out.println("Id:" + u.getId());
        System.out.println("Lang:" + u.getLang());
        System.out.println("ListedCount:" + u.getListedCount());
        System.out.println("ProfileBackgroundColor:"
            + u.getProfileBackgroundColor());
        System.out.println("ProfileBackgroundImageUr:"
            + u.getProfileBackgroundImageUrl());
        System.out.println("ProfileImageURL:" + u.getProfileImageURL());
        System.out.println("ProfileLinkColor:" + u.getProfileLinkColor());
        System.out.println("ProfileSidebarBorderColor:"
            + u.getProfileSidebarBorderColor());
        System.out.println("ProfileSidebarFillColor:"
            + u.getProfileSidebarFillColor());
        System.out.println("ProfileTextColor:" + u.getProfileTextColor());
        System.out.println("RateLimitStatus:" + u.getRateLimitStatus());
        System.out.println("Status:" + u.getStatus());
        System.out.println("StatusesCount:" + u.getStatusesCount());
        System.out.println("TimeZone:" + u.getTimeZone());
        System.out.println("URL:" + u.getURL());
        System.out.println("UtcOffset:" + u.getUtcOffset());


        // 表示だけが違うのでここでjspを判別する
        if (name != "me") {
            return forward("otherPage.jsp");
        }
        return forward("myPage.jsp");
    }
}
