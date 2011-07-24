package unity.service;

import java.util.HashSet;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.UserMeta;
import unity.model.Tweet;
import unity.model.User;

import com.google.appengine.api.datastore.Key;

public class UserService {

    public void save(User u) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(u);
        tx.commit();
    }

    public void regist(long userId, String userName, String myself) {

        User check =
            Datastore
                .query(User.class)
                .filter(UserMeta.get().userId.equal(userId))
                .asSingle();

        if (check == null) {

            User u = new User();
            u.setKey(Datastore.allocateId(User.class));
            u.setUserId(userId);
            u.setUserName(userName);
            u.setMyself(myself);
            u.setWebUrl("設定されていません");
            u.setTweets(new HashSet<Tweet>());

            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(u);
            tx.commit();

        }
    }

    public User getUser(long userId) {

        return Datastore
            .query(User.class)
            .filter(UserMeta.get().userId.equal(userId))
            .asSingle();
    }

    public User getName(String name) {

        // アカウント名からモデルのuserIdを取り出す
        return Datastore
            .query(User.class)
            .filter(UserMeta.get().userName.equal(name))
            .asSingle();
    }

    public User getUser(Key userkey) {
        return Datastore.get(User.class, userkey);
    }

    public void setWebUrl(Long id, String webUrl) {

        User user = getUser(id);
        user.setWebUrl(webUrl);
        save(user);
    }

    public void setMyself(Long id, String webUrl) {

        User user = getUser(id);
        user.setMyself(webUrl);
        save(user);
    }
}
