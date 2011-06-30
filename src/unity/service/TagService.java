package unity.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.RelationTagMeta;
import unity.meta.TagGameMeta;
import unity.meta.TagMeta;
import unity.model.GameData;
import unity.model.RelationTag;
import unity.model.Tag;
import unity.model.TagGame;

import com.google.appengine.api.datastore.Key;

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
            tx.put(tag);
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

    public void conflictTag(Key gameKey) {

        GameData g = Datastore.get(GameData.class, gameKey);
        Set<Tag> fixTags = g.getFixTags();
        Set<Tag> tags = g.getTags();
        if (tags == null) {
            tags = new TreeSet<Tag>();
        }
        Object[] array = tags.toArray();
        for (Object s : array) {

            boolean c = fixTags.contains(s);
            if (c) {
                tags.remove(s);
            }

        }
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(g);
        tx.commit();

    }

    public void setRelation(Key gameDataKey) {

        List<TagGame> list =
            Datastore
                .query(TagGame.class)
                .filter(TagGameMeta.get().gameRef.equal(gameDataKey))
                .asList();
        if (list.size() > 1) {
            Set<String> tagSet = new TreeSet<String>();
            for (TagGame tagGame : list) {
                Tag tag =
                    Datastore.get(Tag.class, tagGame
                        .getTagRef()
                        .getModel()
                        .getKey());
                tagSet.add(tag.getName());
            }
            Object[] array = tagSet.toArray();

            Set<String> set = new TreeSet<String>();

            for (int i = 0; i < array.length; i++) {

                for (int j = 0; j < array.length; j++) {

                    if (array[i].hashCode() <= array[j].hashCode())
                        set.add("" + array[i] + "," + array[j]);
                    else
                        set.add("" + array[j] + "," + array[i]);

                }

            }
            for (String string : set) {

                String[] split = string.split(",");
                String strTag1 = split[0];
                String strTag2 = split[1];

                // System.out.println("tag1:"+strTag1+"tag2:"+strTag2);

                // TagのKey取得
                Key tag1 =
                    Datastore
                        .query(Tag.class)
                        .filter(TagMeta.get().name.equal(strTag1))
                        .asSingle()
                        .getKey();
                Key tag2 =
                    Datastore
                        .query(Tag.class)
                        .filter(TagMeta.get().name.equal(strTag2))
                        .asSingle()
                        .getKey();

                // System.out.println("tag1:"+tag1+"tag2:"+tag2);

                // RelationTagがあるか調べる
                RelationTag rt =
                    Datastore
                        .query(RelationTag.class)
                        .filter(RelationTagMeta.get().tag1.equal(tag1))
                        .filter(RelationTagMeta.get().tag2.equal(tag2))
                        .asSingle();
                // 新規の場合
                if (rt == null) {
                    rt = new RelationTag();
                    rt.setTag1(tag1);// 新規
                    rt.setTag2(tag2);// 新規

                }
                // rt.getGames()にgameKeyが含まれていたらカウントしない
                if (!rt.getGames().contains(gameDataKey))
                    rt.setRelationCount(rt.getRelationCount() + 1);// 新規　更新

                rt.getGames().add(gameDataKey); // 新規　更新
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                tx.put(rt);
                tx.commit();

            }
        }
    }

    public void deleteRelationTag(Key gameKey) {

        List<RelationTag> asList =
            Datastore
                .query(RelationTag.class)
                .filter(RelationTagMeta.get().games.equal(gameKey))
                .asList();
        GameData g = Datastore.get(GameData.class, gameKey);

        // まずtagを1つにする
        Set<Tag> fixTags = g.getFixTags();
        Set<Tag> tags = g.getTags();
        
        fixTags.addAll(tags);
        Set<String> set = new TreeSet<String>();
        for (Tag tag : fixTags) {

            set.add(tag.getName());
        }

        getSetString(set);

        Set<String> set2 = new TreeSet<String>();
        for (RelationTag r : asList) {
            String tag1 = Datastore.get(Tag.class, r.getTag1()).getName();
            String tag2 = Datastore.get(Tag.class, r.getTag2()).getName();
            // System.out.println("tag1:"+tag1+"tag2:"+tag2);
            if (tag1.hashCode() <= tag2.hashCode())
                set2.add("" + tag1 + "," + tag2);
            else
                set2.add("" + tag1 + "," + tag2);

        }

        for (String s : set2) {

            boolean c = set.contains(s);
            if (!c) {
                // ここでgameKey消す
                String[] ta = s.split(",");
                Key tag1 =
                    Datastore
                        .query(Tag.class)
                        .filter(TagMeta.get().name.equal(ta[0]))
                        .asSingle()
                        .getKey();
                Key tag2 =
                    Datastore
                        .query(Tag.class)
                        .filter(TagMeta.get().name.equal(ta[1]))
                        .asSingle()
                        .getKey();
                RelationTag rt =
                    Datastore
                        .query(RelationTag.class)
                        .filter(RelationTagMeta.get().tag1.equal(tag1))
                        .filter(RelationTagMeta.get().tag2.equal(tag2))
                        .asSingle();
                rt.getGames().remove(gameKey);
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                tx.put(rt);
                tx.commit();
            }
        }
    }

    public Set<String> getSetString(Set<String> setStr) {

        Object[] array = setStr.toArray();
        setStr.clear();
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length; j++) {

                if (array[i].hashCode() <= array[j].hashCode())
                    setStr.add("" + array[i] + "," + array[j]);
                else
                    setStr.add("" + array[j] + "," + array[i]);

            }

        }

        return setStr;

    }
}