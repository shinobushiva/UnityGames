package unity.controller.api;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.tester.ControllerTestCase;

import unity.model.GameData;
import unity.model.User;
import unity.model.api.GameSaveData;
import unity.service.SaveDataService;

public class EvalControllerTest extends ControllerTestCase {
    SaveDataService sd = new SaveDataService();

    @Test
    public void run() throws Exception {
        Long gameId = createGame().getKey().getId();
        Long user1 = createUser().getKey().getId();
        Long user2 = createUser().getKey().getId();
        System.out.println("user1 : " + user1);
        System.out.println("user2 : " + user2);
        // private
        sd.setSaveData(gameId, user1, "private", "新規");
        sd.setSaveData(gameId, user1, "private", "更新");
        // public
        sd.setSaveData(gameId, user1, "public", "新規");
        sd.setSaveData(gameId, null, "public", "更新");
       
        
       
        
        GameSaveData s = sd.getSaveData(gameId, null, "public");
        System.out.println(s.getGameRef().getKey());
        System.out.println(s.getUserRef().getKey());
        System.out.println(s.getSaveType());
        System.out.println(s.getSaveData());
        
//       tester.start("/api/eval");
    }

    GameData createGame() {
        GameData g = new GameData();
        g.setKey(Datastore.allocateId(GameData.class));
        save(g);
        return g;
    }

    User createUser() {
        User u = new User();
        u.setKey(Datastore.allocateId(User.class));
        save(u);
        return u;
    }

    void save(Object o) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(o);
        tx.commit();
    }
}
