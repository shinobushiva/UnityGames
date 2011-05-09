package unity.service;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.UserMeta;
import unity.model.User;

public class UserService {

    public User regist(long userId, String userName) {

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

            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            Datastore.put(u);
            tx.commit();

        }
        return null;
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
