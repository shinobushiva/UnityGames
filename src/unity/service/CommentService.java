package unity.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.CommentMeta;
import unity.meta.RelationTagMeta;
import unity.meta.TagGameMeta;
import unity.meta.TagMeta;
import unity.meta.UserMeta;
import unity.model.Comment;
import unity.model.GameData;
import unity.model.RelationTag;
import unity.model.Tag;
import unity.model.TagGame;
import unity.model.User;

import com.google.appengine.api.datastore.Key;

public class CommentService {
    GameDataService gs = new GameDataService();

    public void commentUp(Key key, String com, String twitterId) {
        Key commentKey = Datastore.allocateId(key, Comment.class);
        Comment comment = new Comment();

        comment.setGameKey(key);

        comment.setKey(commentKey);
        comment.setComment(com);
        comment.setDate(new Date());
        comment.setTwitterId("" + twitterId);
        GameData g = Datastore.get(GameData.class, key);
        g.setComment(g.getComment() + 1);
        Datastore.put(g);
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(comment);
        tx.commit();
        gs.addPoint(g);
    }

    public List<Comment> getCommentsDesc(Key key) {
        return Datastore
            .query(Comment.class)
            .filter(CommentMeta.get().gameKey.equal(key))
            .sort(CommentMeta.get().date.desc)
            .asList();
    }

    public List<Comment> getCommentsAsc(Key key) {
        return Datastore
            .query(Comment.class)
            .filter(CommentMeta.get().gameKey.equal(key))
            .sort(CommentMeta.get().date.asc)
            .asList();
    }
    
    public void setCommentUser(List<Comment> comments) {
        for (Comment comment : comments) {
            String userName = "Guest";
            if (!comment.getTwitterId().isEmpty()) {
                Long userId = Long.valueOf((comment.getTwitterId()));
                User user =
                    Datastore
                        .query(User.class)
                        .filter(UserMeta.get().userId.equal(userId))
                        .asSingle();
                userName = user.getUserName();
            }
            comment.setTwitterId(userName);
        }
    }
}