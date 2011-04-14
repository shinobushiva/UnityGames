package unity.controller.unitygames;

import java.net.URI;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import unity.meta.CommentMeta;
import unity.meta.GameDataMeta;
import unity.meta.TagMeta;
import unity.model.Comment;
import unity.model.GameData;
import unity.model.Tag;

public class GameController extends Controller {

    private GameDataMeta dd = GameDataMeta.get();

    @Override
    public Navigation run() throws Exception {

        // どっからきたかを確認してリロードした時アクセス数を増やさないようにしたい（分からないので保留）
        StringBuffer path = request.getRequestURL();
        path.append("?").append(request.getQueryString());
        // String path = new URI(request.getHeader("referrer")).getPath();

        System.out.println(path);
        long id = asLong("id");

        GameData g =
            Datastore.get(
                GameData.class,
                KeyFactory.createKey(dd.getKind(), id));
        requestScope("key", g.getKey());

        Transaction tx = Datastore.beginTransaction();
        g.setAccess(g.getAccess() + 1);
        Datastore.put(g);
        tx.commit();

        requestScope("g", g);

        // tagを表示
        // Tag tag = Datastore.query(Tag.class,g.getKey()).asSingle();
        //
        // String str = tag.getTag();
        // String fixStr = tag.getFixTag();
        // if(str != null){
        // String[] Tag = str.split(",");
        // requestScope("tag",Tag);
        // }
        // String[] fixTag = fixStr.split(",");
        //
        // requestScope("fixTag",fixTag );
        // System.out.println(fixTag);
        //
        // コメント表示
        // List<Child> list = Datastore.query(Child.class,
        // ancestorKey).asList();
        List<Comment> comment =
            Datastore
                .query(Comment.class, g.getKey())
                .sort(CommentMeta.get().date.asc)
                .asList();

        // List<Comment> comment =
        // Datastore.query(Comment.class).filter(CommentMeta.get().gameDataKey.equal(g.getKey())).sort(CommentMeta.get().date.asc).asList();
        requestScope("c", comment);
        for (Comment co : comment) {
            long l = co.getDate().getTime() + 1000 * 60 * 60 * 9;
            co.getDate().setTime(l);
        }

        // 編集中のみ隠す
        // if(g.getGameURL().isEmpty()){
        // requestScope("play","unityObject.embedUnity('unityPlayer','/unitygames/GameData?id="+g.getKey().getId()+"', 600, 450);");
        //
        // }else{
        // requestScope("play","unityObject.embedUnity('unityPlayer','"+g.getGameURL()+"', 600, 450);");
        // }
        // ここまで

        return forward("Game.jsp");
    }
}
