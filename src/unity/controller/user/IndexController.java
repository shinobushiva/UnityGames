package unity.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.sun.org.apache.bcel.internal.generic.NEW;

import twitter4j.Twitter;
import unity.meta.GameDataMeta;
import unity.model.GameData;
import unity.model.Tweet;
import unity.service.LoginService;
import unity.service.UserService;

public class IndexController extends Controller {
    private LoginService ls = new LoginService();
    private UserService us = new UserService();

    @Override
    public Navigation run() throws Exception {

        String name = asString("name");

        // ログイン
        // Twitter t = (Twitter) sessionScope("twitter");
        // if (t == null) {
        // return redirect("/login/oAuth?name=" + name);
        // }

        boolean isMe = false;
        // System.out.println("誰が見てる？"+t.getScreenName());
        if (ls.isLogin(request)) {
            String userName = ls.getLoginUserName(request);
            if (name.equals(userName)) {
                isMe = true;
            }
            // ページを見ている人が本人か他人かを判別
            // ローカルなのでこっちのifつかってる
            // if (name.equals("kyusyukeigo")) {

            // requestScope("name", "myName");
        }
        System.out.println(ls.isLogin(request));

        // アカウント名からモデルのuserIdを取り出す
        unity.model.User uk = us.getName(name);
        System.out.println("uk:" + uk);
        requestScope("twitterId", uk.getUserId());

        // TweetIdを取り出す
        Set<Tweet> tweets = uk.getTweets();

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
        // 該当したtweetのリスト
        requestScope("tweet", tweets);
        // モデルのUser情報
        requestScope("um", uk);
        // Twitterアカウント
        // requestScope("u", u);
        // TwitterProfilePicture
        // requestScope("tp", picture);

        // 表示だけが違うのでここでjspを判別する
        if (!isMe) {
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
