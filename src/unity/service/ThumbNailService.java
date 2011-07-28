package unity.service;

import org.slim3.datastore.Datastore;

import unity.model.GameData;
import unity.model.ThumbNailData;

public class ThumbNailService {

    public ThumbNailData getThumbNailData(Long id) {

        return Datastore.query(
            ThumbNailData.class,
            Datastore.createKey(GameData.class, id)).asSingle();
    }
}
