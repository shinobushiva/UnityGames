package unity.util;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.model.api.Game;
import unity.model.feed.FeedCreator;

public class feed extends Controller {

   
   
    @Override
    public Navigation run() throws Exception {
        
        List<Game> findAll = Datastore.query(Game.class).asList();
       
        FeedCreator feed = new FeedCreator("rss_2.0");
        feed.setFeedTitle("UnityGames");
        feed.setFeedLink("http://unity-games.appspot.com/");
        feed.setFeedDescription("Unityゲーム投稿サイト");
       
        for (Game g : findAll) {
         
            feed.setTextEntry(g.getGameName(),"http://unity-games.appspot.com/unitygames/game/"+g.getGameId(), g.getExplanation(), g.getEntry());
            
        }
        feed.createFeed();
        return null;
       
    }
}
