package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import unity.meta.api.GameMeta;
import unity.model.api.Game;

public class ApiService {

    public Game find(String ids) {

        return Datastore
            .query(Game.class)
            .filter(GameMeta.get().gameId.equal(ids))
            .asSingle();

    }

    public List<Game> findAll() {

        return Datastore
            .query(Game.class)
            .sort(GameMeta.get().entry.desc)
            .asList();

    }

    public List<Game> rss() {

        return Datastore
            .query(Game.class)
            .sort(GameMeta.get().entry.asc)
            .limit(100)
            .asList();

    }

}
