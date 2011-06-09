package unity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.arnx.jsonic.JSON;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.GameData;
import unity.utils.CodeBlockUtils;

public class GameDataService {

    public void save(GameData c) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(c);
        tx.commit();

    }

    public GameData load(long id) {

        return Datastore.get(
            GameData.class,
            Datastore.createKey(GameData.class, id));

    }

    public GameData addPoint(GameData g) {

        int point = g.getAccess() + g.getComment() * 3;

        g.setPoint(point);

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.commit();

        return null;
    }

    public String toCodeJson(String str) {
        return CodeBlockUtils.toCodeJson(str);

    }

}
