package unity.service;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.TagMeta;
import unity.model.Tag;

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

}
