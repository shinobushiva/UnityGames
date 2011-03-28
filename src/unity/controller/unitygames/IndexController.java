package unity.controller.unitygames;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;


import unity.meta.GameDataMeta;
import unity.meta.NoteMeta;

import unity.model.Note;
import unity.model.GameData;

public class IndexController extends Controller {
 
    private GameDataMeta g = GameDataMeta.get();
    private NoteMeta n = NoteMeta.get();
   
    @Override
    public Navigation run() throws Exception {
        
        
        List<GameData> Game = Datastore.query(g).asList();
          
        System.out.println(Game);
        
        
        
        requestScope("GameList",Game);
        for(GameData game : Game){
            long l = game.getDate().getTime() + 1000 * 60 * 60 * 9;
            game.getDate().setTime(l);
            
        }
        
//List<Note> note =Datastore.query(n).asList();
//    requestScope("note",note);
//    for(Note notee: note){
//
//       System.out.println(notee.getContents()); 
//    }
        
        
        return forward("index.jsp");
    }
}
