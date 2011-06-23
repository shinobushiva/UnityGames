package unity.service;

import java.util.HashSet;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.UserMeta;
import unity.model.Tweet;
import unity.model.User;

public class UserService {

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
}
