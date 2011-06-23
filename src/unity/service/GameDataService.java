package unity.service;

import java.util.Date;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.CommentaryLog;
import unity.model.GameData;
import unity.model.api.Game;
import unity.utils.CodeBlockUtils;

import com.google.appengine.api.datastore.Key;

public class GameDataService {

    public void save(GameData c) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(c);
        tx.commit();

    }

    public GameData load(long id) {

        return Datastore.get(
            GameData.class,
            Datastore.createKey(GameData.class, id));

    }

    public GameData addPoint(GameData g) {

        int point = g.getAccess() + g.getComment() * 3;

        g.setPoint(point);

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.commit();

        return null;
    }

    public String toCodeJson(String str) {
        return CodeBlockUtils.toCodeJson(str);

    }

    public CommentaryLog commentaryLog(Key gameKey, String commentary) {

        CommentaryLog cl = new CommentaryLog();
        cl.setKey(Datastore.allocateId(CommentaryLog.class));
        cl.setGameKey(gameKey);
        cl.setCommentary(commentary);
        cl.setDate(new Date());

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(cl);
        tx.commit();

        return null;
    }

    public Game getGameApi(Key key) {

        return Datastore.get(Game.class, key);
    }

    // public Set<GameData> relationGame(Key gameKey) {
    //
    // List<RelationTag> rt =
    // Datastore
    // .query(RelationTag.class)
    // .filter(RelationTagMeta.get().games.equal(gameKey))
    // .sort(RelationTagMeta.get().relationCount.desc)
    // .asList();
    //
    // Set<GameData> gameList = new HashSet<GameData>();
    //
    // for (RelationTag relationTag : rt) {
    // for (Key key : relationTag.getGames()) {
    // GameData gg = Datastore.get(GameData.class, key);
    // if (!gg.getKey().equals(gameKey)) {
    // gameList.add(gg);
    // }
    // }
    // }
    // return gameList;
    // }

}
