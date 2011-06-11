package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.model.api.Game;

public class UpDateController extends Controller {

    @Override
    public Navigation run() throws Exception {

        List<GameData> asList = Datastore.query(GameData.class).asList();
        List<Game> asList2 = Datastore.query(Game.class).asList();
        for (GameData g : asList) {

//            g.setEditCode(false);
//
//            GlobalTransaction tx = Datastore.beginGlobalTransaction();
//            tx.put(g);
//            tx.commit();

        }

        for (Game game : asList2) {
            // game.setGameScreenSize(size);
            //
            // GlobalTransaction tx = Datastore.beginGlobalTransaction();
            // tx.put(game);
            // tx.commit();
        }

        return null;
    }
}
