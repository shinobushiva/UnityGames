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
    public static final int PRIVATE_BEST = 2;
    public static final int PRIVATE_BETTER = 1;
    public static final int PUBLIC_GAME = 0;

    public Set<String> suggestionWords() {

        Set<String> sw = new HashSet<String>();
        try {
            InputSearh is = Datastore.query(InputSearh.class).asSingle();
            if (is != null)
                sw = is.getSuggestionWords();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw;
    }

    public List<GameData> searchWord(String word) {

        List<GameData> gds = new ArrayList<GameData>();
        for (GameData g : gs.getAll()) {
            if (g
                .getGameName()
                .toLowerCase()
                .contains(word.trim().toLowerCase())
                && g.getAccessLevel() == PUBLIC_GAME) {
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
                    .contains(tag.trim().toLowerCase())
                    && g.getAccessLevel() == PUBLIC_GAME)
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
