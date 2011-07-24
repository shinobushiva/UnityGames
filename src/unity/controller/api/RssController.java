package unity.controller.api;

import java.io.PrintWriter;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import unity.model.api.Game;
import unity.model.feed.FeedCreator;
import unity.service.ApiService;

public class RssController extends Controller {

    private ApiService as = new ApiService();

    @Override
    public Navigation run() throws Exception {

        List<Game> rss = as.rss();

        FeedCreator feed = new FeedCreator("rss_2.0");
        feed.setFeedTitle("UnityGames");
        feed.setFeedLink("http://unity-games.appspot.com/");
        feed.setFeedDescription("Unityゲーム投稿サイト");

        for (Game g : rss) {

                feed.setTextEntry(
                    g.getGameName(),
                    "http://unity-games.appspot.com/unitygames/game/"
                        + g.getGameId(),
                    g.getExplanation()
                        + "<br><img src=\""
                        + g.getThumbNail()
                        + "\">",
                    g.getEntry());
            

        }

        String createFeed = feed.createFeed();

        String replace = createFeed.replace("false", "true");
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(replace);
        out.close();

        return null;
    }
}
