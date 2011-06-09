package unity.controller.showUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import twitter4j.Twitter;
import unity.meta.GameDataMeta;
import unity.model.GameData;
import unity.model.Tweet;

public class MyPageController extends Controller {

    private unity.service.UserService us = new unity.service.UserService();

    @SuppressWarnings({ "unchecked", "unused" })
    @Override
    public Navigation run() throws Exception {

        String name = asString("name");

        unity.model.User uk = null;
        if (name.equals("me")) {

            Twitter t = (Twitter) sessionScope("twitter");
            System.out.println("ScreenName:" + t.getScreenName());
            // ローカル時
            // uk = us.getName("kyusyukeigo");
            uk = us.getName(t.getScreenName());
        } else {

            // アカウント名からモデルのuserIdを取り出す
            uk = us.getName(name);
        }
        System.out.println("uk:" + uk);
        requestScope("twitterId", uk.getUserId());
        // userIdからイメージ画像を持ってくる
        // Twitter twitter = new TwitterFactory().getInstance();
        // twitter4j.User u = twitter.showUser(uk.getUserId());
        // System.out.println("u:" + u);
        //
        // String imageURL = u.getProfileImageURL().toString();
        //
        // String picture = imageURL.replace("normal", "reasonably_small");
        //
        // TweetIdを取り出す
        Set<Tweet> tweets = uk.getTweets();

        /* 毎回1つ1つjsonでStatus(Tweet)idで持ってきてそのツイートがあるかないか確認しながら行う処理 */
        /* でもAPI１５０回制限で厳しいので保留。まとめて取得できるならこのやり方でいく */
        /* 今のところできないので削除は考えず(削除ボタン作ればいいけど2度・・・)モデルに保存する */
        // とってきたTweetをリスト化する準備
        // List<Object> t = new ArrayList<Object>();
        //
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

        // ローカルの時だけ全取得
        // List<GameData> g = Datastore.query(GameData.class).asList();

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
        // モデルのUser情報
        requestScope("um", uk);
        // Twitterアカウント
        // requestScope("u", u);
        // TwitterProfilePicture
        // requestScope("tp", picture);

        // 表示だけが違うのでここでjspを判別する
        if (!name.equals("me")) {
            for (GameData gg : g) {

                // otherPageのときだけugリンク化
                // ug形式の短縮リンク変換
                String tt = gg.getContents();
                String regex = "ug[0-9]*";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(tt);
                List<String> list = new ArrayList<String>();
                while (m.find()) {
                    list.add(m.group());
                    System.out.println("m:" + m.group());
                }
                String ts = tt;
                for (String st : list) {

                    // ugの後が数字でないものはリンク化行わない
                    if (!st.matches("ug[^0-9]*")) {

                        // ugを無くしてidだけを抽出 例）ug1234 → 1234
                        String ug = st.replaceAll("ug", "");

                        String s =

                            ts.replaceAll(
                                st,
                                "<a href='http://unity-games.appspot.com/"
                                    + "unitygames/game/ug"
                                    + ug
                                    + "'class='ugLink'>"
                                    + st
                                    + "</a>");
                        // 繰り返し置換していく
                        ts = s;
                    }

                }
                gg.setContents(ts);
            }
            System.out.println("こっちotherPage");
            return forward("/showUser/otherPage.jsp");
        }
        System.out.println("こっちmyPage");
        return forward("/showUser/myPage.jsp");
    }
}
