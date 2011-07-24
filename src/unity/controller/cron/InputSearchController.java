package unity.controller.cron;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.InputSearh;
import unity.model.Tag;
import unity.model.api.Game;

import com.google.appengine.api.datastore.Key;

public class InputSearchController extends Controller {

    @Override
    protected Navigation run() throws Exception {

        List<Key> asKeyList = Datastore.query(InputSearh.class).asKeyList();
        if (asKeyList != null) {
            for (Key key : asKeyList) {
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                Datastore.delete(key);
                tx.commit();
            }

        }

        List<Game> words = Datastore.query(Game.class).asList();
        Set<String> wordsSet = new HashSet<String>();
        for (Game g : words) {
            wordsSet.add("\"" + g.getGameName() + "\"");
        }

        List<Tag> tags = Datastore.query(Tag.class).asList();
        List<String> tagsArray = new ArrayList<String>();
        for (Tag tag : tags) {
            tagsArray.add("\"" + tag.getName() + "\"");
        }

        InputSearh in = new InputSearh();
        in.setSuggestionWords(wordsSet);
        in.setSuggestionTags(tagsArray);

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(in);
        tx.commit();

        return null;
    }

}
