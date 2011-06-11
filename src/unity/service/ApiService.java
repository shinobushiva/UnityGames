package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import unity.meta.api.GameMeta;
import unity.model.api.Game;

public class ApiService {

    public Game find(String ids) {

        System.out.println(ids);
        return Datastore
            .query(Game.class)
            .filter(GameMeta.get().gameId.equal(ids))
            .asSingle();

    }

    public List<Game> findAll() {

        return Datastore.query(Game.class).asList();

    }

}
