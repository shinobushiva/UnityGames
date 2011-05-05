package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import unity.meta.GameDataMeta;
import unity.model.GameData;

public class ApiService {

    public GameData find(long ids) {

        System.out.println(ids);
        return Datastore.get(
            GameData.class,
            Datastore.createKey(GameData.class, ids));

    }
    public List<GameData> findAll() {
        
        return Datastore.query(GameData.class).asList();
        
    }
}
