package unity.service;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import com.google.appengine.api.datastore.Key;

import unity.meta.TagGameMeta;
import unity.meta.TagMeta;
import unity.model.Tag;
import unity.model.TagGame;

public class TagService {

    public Tag getTag(String name) {

        Tag tag =
            Datastore
                .query(Tag.class)
                .filter(TagMeta.get().name.equal(name))
                .asSingle();
        if (tag == null) {

            tag = new Tag();
            tag.setName(name);
            tag.setKey(Datastore.allocateId(Tag.class));

            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            Datastore.put(tag);
            tx.commit();

        }
        return tag;
    }

    public TagGame getTagGame(Key gameDataKey, Key tagKey) {

        TagGame tg =
            Datastore
                .query(TagGame.class)
                .filter(TagGameMeta.get().gameRef.equal(gameDataKey))
                .filter(TagGameMeta.get().tagRef.equal(tagKey))
                .asSingle();

        return tg;
    }

}
