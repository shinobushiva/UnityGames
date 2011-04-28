package unity.controller.unitygames;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import unity.meta.CommentMeta;
import unity.meta.GameDataMeta;
import unity.model.Comment;
import unity.model.GameData;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class GameController extends Controller {

    private GameDataMeta dd = GameDataMeta.get();

    @Override
    public Navigation run() throws Exception {

        String remoteAddr = request.getRemoteAddr();

        System.out.println(remoteAddr);

        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));
        requestScope("key", g.getKey());
        if (!remoteAddr.equals(Memcache.get("lastIp-" + g.getGameName()))) {
            Memcache.put("lastIp-" + g.getGameName(), remoteAddr);
            Transaction tx = Datastore.beginTransaction();
            g.setAccess(g.getAccess() + 1);
            Datastore.put(g);
            tx.commit();
        }

        // ug形式の短縮リンク変換
        String t = g.getContents();
        String regex = "ug[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(t);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group());
        }
        String ts = t;
        for (String st : list) {
            //ugを無くしてidだけを抽出　例）ug1234　→ 1234　
            String ug = st.replaceAll("ug", "");
            String s =

                ts.replaceAll(
                    st,
                    "<a href='http://unity-games.appspot.com/unitygames/game?id="
                        + ug
                        + "'class='ugLink'>"
                        + st
                        + "</a>");
            //繰り返し置換していく
            ts = s;

        }
        g.setContents(ts);

        requestScope("g", g);

        List<Comment> comment =
            Datastore
                .query(Comment.class, g.getKey())
                .sort(CommentMeta.get().date.asc)
                .asList();
        requestScope("c", comment);
        // for (Comment co : comment) {
        // long l = co.getDate().getTime() + 1000 * 60 * 60 * 9;
        // co.getDate().setTime(l);
        // }

        return forward("game.jsp");
    }
}
