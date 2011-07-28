package unity.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slim3.datastore.Datastore;

import unity.model.GameData;
import unity.model.InputSearh;
import unity.model.TagGame;

public class SearchService {
    private GameDataService gs = new GameDataService();
    private TagService ts = new TagService();

    public Set<String> suggestionWords() {

        Set<String> sw = new HashSet<String>();

        InputSearh is = Datastore.query(InputSearh.class).limit(1).asSingle();
        if (is != null)
            sw = is.getSuggestionWords();
        return sw;
    }

    public List<GameData> searchWord(String word) {

        List<GameData> gds = new ArrayList<GameData>();
        for (GameData g : gs.getAll()) {
            if (g
                .getGameName()
                .toLowerCase()
                .contains(word.trim().toLowerCase())) {
                gds.add(g);
            }
        }
        gs.contentCut(gds);

        System.out.println("サービス");
        for (GameData gameData : gds) {
            System.out.println(gameData.getGameName());
        }

        return gds;
    }

    public List<GameData> searchTag(String tag) {
        List<GameData> gds = new ArrayList<GameData>();
        Boolean check = false;
        for (GameData g : gs.getAll()) {
            List<TagGame> asList = ts.getSearchGame(g);
            for (TagGame tagGame : asList) {
                if (tagGame
                    .getTagRef()
                    .getModel()
                    .getName()
                    .toLowerCase()
                    .contains(tag.trim().toLowerCase()))
                    check = true;
            }
            if (check) {
                gds.add(g);
                check = false;
            }
        }
        gs.contentCut(gds);
        return gds;
    }
}
