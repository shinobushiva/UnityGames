package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;

public class UpDateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        List<GameData> asList = Datastore.query(GameData.class).asList();

        for (GameData gameData : asList) {

            int point = gameData.getAccess() + gameData.getComment() * 3;

            gameData.setPoint(point);

            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            Datastore.put(gameData);
            tx.commit();

        }

        return null;
    }
}
