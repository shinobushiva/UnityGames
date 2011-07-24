package unity.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import unity.meta.api.GameMeta;
import unity.model.GameData;
import unity.model.ThumbNailData;
import unity.model.api.Game;

public class ThumbNailService {

    public ThumbNailData getThumbNailData(Long id) {

        return Datastore.query(
            ThumbNailData.class,
            Datastore.createKey(GameData.class, id)).asSingle();
    }
}
