package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.model.api.Game;
import unity.service.GameDataService;

public class UpDateController extends Controller {

    private GameDataService gs = new GameDataService(); 
    @Override
    public Navigation run() throws Exception {

        List<GameData> asList = Datastore.query(GameData.class).asList();
//        List<Game> asList2 = Datastore.query(Game.class).asList();
        for (GameData g : asList) {

          
            
//            Game game = new Game();
//            
//            game.setAccess(g.getAccess());
//            game.setCode(g.getCode());
//            game.setComments(g.getComment());
//            game.setEntry(g.getDate());
//            game.setExplanation(g.getContents());
//            game.setFixTags(g.getFixTags());
//           
//            if(g.getGameType().equals("data"))
//                game.setGame("http://unity-games.appspot.com/unitygames/GameData?id="+g.getKey().getId());
//            else
//                game.setGame(g.getGameURL());
//                    
//            game.setGameId("ug"+g.getKey().getId());
//            game.setGameName(g.getGameName());
//            game.setGameScreenSize(g.getGameScreenSize());
//            game.setKey(Datastore.allocateId(Game.class));
//            game.setLastDate(g.getLastDate());
//            game.setOperations(g.getOperations());
//            game.setTags(g.getTags());
//            
//            if(g.getThumbNailType().equals("data"))
//                game.setThumbNail("http://unity-games.appspot.com/unitygames/thumbNail?id="+g.getKey().getId());
//            else
//                game.setThumbNail(g.getThumbNailURL());
//            
//            
//            GlobalTransaction tx = Datastore.beginGlobalTransaction();
//            tx.put(game);
//            tx.commit();

        }

//        for (Game g : asList2) {
         
//            String ids = g.getGameId();
//            String replace = ids.replace("ug", "");
//            long id = Long.valueOf(replace);
//            GameData gg = gs.load(id);
//            if(gg.getThumbNailType() == "data"){
//                
//                g.setThumbNail("http://unity-games.appspot.com/unitygames/thumbNail?id="+gg.getKey().getId());
//                
//            }else{
//                g.setThumbNail(gg.getThumbNailURL());
//            }
           
//         g.set
//            
//             GlobalTransaction tx = Datastore.beginGlobalTransaction();
//             tx.put(g);
//             tx.commit();
//        }
       
 
        return null;
    }
}
