package unity.controller.unitygames;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.memcache.Memcache;

import unity.meta.CommentMeta;
import unity.model.Comment;
import unity.model.GameData;
import unity.service.GameDataService;

public class GameController extends Controller {

    private GameDataService gs = new GameDataService();

    @Override
    public Navigation run() throws Exception {
        String remoteAddr = request.getRemoteAddr();
        // gameIdからgameを持ってくる
        long id = asLong("id");

        GameData g = gs.load(id);

        // ds.depthFirstSearch(g.getKey());

        requestScope("key", g.getKey());

        if (!remoteAddr.equals(Memcache.get("lastIp-" + g.getGameName()))) {
            Memcache.put("lastIp-" + g.getGameName(), remoteAddr);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            g.setAccess(g.getAccess() + 1);
            tx.put(g);
            tx.commit();
            gs.addPoint(g);
        }
        // 投稿者 Twitterアカウント表示
        if (g.getTwitterUserKey() != null) {
            unity.model.User uk =
                Datastore.get(unity.model.User.class, g.getTwitterUserKey());

            requestScope("twitterId", uk.getUserId());

        }

        // ug形式の短縮リンク変換
        String t = g.getContents();
        String regex = "ug[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(t);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group());
            System.out.println("m:" + m.group());
        }
        String ts = t;
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
        g.setContents(ts);

        requestScope("jscode", gs.toCodeJson(g.getCode()));

        requestScope("g", g);
        // コメント部分
        List<Comment> comment =
            Datastore
                .query(Comment.class, g.getKey())
                .sort(CommentMeta.get().date.asc)
                .asList();

        requestScope("c", comment);

        // 関連ゲーム部分
        // List<Key> asKeyList =
        // Datastore.query(RelationTag.class).filter(RelationTagMeta.get().games.equal(g.getKey())).asKeyList();

        // Set<GameData> gameList = gs.relationGame(g.getKey());

        // requestScope("relation", gameList);

        // ログイン
        requestScope("isLogin", (Boolean) sessionScope("isLogin"));
        requestScope("twitter", sessionScope("twitter"));
        return forward("game.jsp");
    }
}
