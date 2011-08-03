package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import unity.meta.api.GameMeta;
import unity.model.api.Game;
import unity.model.feed.FeedCreator;

public class RSSService {

    public static final String MEMCACHE_KEY_RSS = "rss";

    public String getRSSString() {
        String r = Memcache.get(MEMCACHE_KEY_RSS);
        if (r == null) {
            updateRSSString();
            r = Memcache.get(MEMCACHE_KEY_RSS);
        }

        return r;
    }

    public void updateRSSString() {
        List<Game> games =
            Datastore.query(Game.class).sort(GameMeta.get().entry.asc).asList();

        FeedCreator feed = new FeedCreator("rss_2.0");
        feed.setFeedTitle("UnityGames");
        feed.setFeedLink("http://unity-games.appspot.com/");
        feed.setFeedDescription("Unityゲーム投稿サイト");

        for (Game g : games) {

            feed.setTextEntry(
                g.getGameName(),
                "http://unity-games.appspot.com/unitygames/game/"
                    + g.getGameId(),
                g.getExplanation()
                    + "<br><img src=\""
                    + g.getThumbNail()
                    + "\"width=\"100px\"height=\"100px\">",
                g.getEntry());

        }

        String createFeed = feed.createFeed();
        String replace = createFeed.replace("false", "true");

        Memcache.put(MEMCACHE_KEY_RSS, replace);

    }

}
