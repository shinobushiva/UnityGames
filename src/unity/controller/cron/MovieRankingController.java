package unity.controller.cron;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.model.MovieRanking;

import com.google.appengine.api.datastore.Key;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;

public class MovieRankingController extends Controller {
    YouTubeService service = new YouTubeService("UnityGames", "AI39si61TWwbwkO47HOjA5fhUA7frI7p85jTkjceTM4pi3JYm3R7P8b-OB1OOW0YMCwrTqxOpBYKeRE4EbioM5ZrrWGebrppww");
   
    @Override
    protected Navigation run() throws Exception {
        
        List<Key> asKeyList = Datastore.query(MovieRanking.class).asKeyList();
        if (asKeyList != null) {
            for (Key key : asKeyList) {
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                Datastore.delete(key);
                tx.commit();
            }

        }

        List<GameData> movieRanking = Datastore.query(GameData.class).asList();
        for (GameData game : movieRanking) {
            Pattern p =
                Pattern
                    .compile(".*(youtu(?:\\.be|be\\.com)/(?:.*v(?:/|=)|(?:.*/)?)([a-zA-Z0-9-_]+)).*",
                        Pattern.DOTALL);
            Matcher matches = p.matcher(game.getCode());
            if (matches.matches()) {

                String videoEntryUrl = "http://gdata.youtube.com/feeds/api/videos/"+matches.group(2);
                VideoEntry videoEntry = service.getEntry(new URL(videoEntryUrl), VideoEntry.class);
                System.out.println("Title: " + videoEntry.getTitle().getPlainText());
                System.out.println(videoEntry.getStatistics().getViewCount());
             
                MovieRanking mr = new MovieRanking();
                mr.setPoint((int)videoEntry.getStatistics().getViewCount());
                mr.getGameRef().setModel(game);
                mr.setDate(new Date());
                mr.setMovieUrl("<a rel='video' href='http://www.youtube.com/watch?v="
                    + matches.group(2)
                    + "'></a>");
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                Datastore.put(mr);
                tx.commit();

            }
        }

        return null;
    }
}