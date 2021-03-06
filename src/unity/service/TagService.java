package unity.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.RelationTagMeta;
import unity.meta.TagGameMeta;
import unity.meta.TagMeta;
import unity.model.GameData;
import unity.model.InputSearh;
import unity.model.RelationTag;
import unity.model.Tag;
import unity.model.TagGame;
import unity.model.api.Game;

import com.google.appengine.api.datastore.Key;

public class TagService {
    GameDataService gs = new GameDataService();

    public void save(Tag t) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(t);
        tx.commit();

    }

    public void save(TagGame tg) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(tg);
        tx.commit();

    }

    public void save(RelationTag rg) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(rg);
        tx.commit();

    }

    public void delete(Key t) {
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.delete(t);
        tx.commit();
    }

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

            save(tag);

        }
        return tag;
    }

    public void deleteTagGame(Key gameDataKey, Key tagKey) {

        TagGame tg =
            Datastore
                .query(TagGame.class)
                .filter(TagGameMeta.get().gameRef.equal(gameDataKey))
                .filter(TagGameMeta.get().tagRef.equal(tagKey))
                .asSingle();

        delete(tg.getKey());

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
        gs.save(g);

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
                save(rt);

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
                save(rt);
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

    public List<TagGame> getSearchGame(GameData g) {
        return Datastore
            .query(TagGame.class)
            .filter(TagGameMeta.get().gameRef.equal(g.getKey()))
            .asList();
    }

    public Tag getTag(Long id) {
        return Datastore.get(Tag.class, Datastore.createKey(Tag.class, id));
    }

    public void createTagGame(GameData g, Tag t) {
        TagGame tt = new TagGame();
        tt.getGameRef().setKey(g.getKey());
        tt.getTagRef().setModel(t);
        save(tt);
    }

    public List<TagGame> getTagGames(Key tKey) {
        return Datastore
            .query(TagGame.class)
            .filter(TagGameMeta.get().tagRef.equal(tKey))
            .asList();
    }

    public void inputSearch() {
        List<Key> asKeyList = Datastore.query(InputSearh.class).asKeyList();
        if (asKeyList != null) {
            for (Key key : asKeyList) {
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                Datastore.delete(key);
                tx.commit();
            }

        }
        List<Game> words = Datastore.query(Game.class).asList();
        Set<String> wordsSet = new HashSet<String>();
        for (Game g : words) {
            wordsSet.add("\"" + g.getGameName() + "\"");
        }
        List<Tag> tags = Datastore.query(Tag.class).asList();
        for (Tag tag : tags) {
            wordsSet.add("\"" + tag.getName() + "\"");
        }

        InputSearh in = new InputSearh();
        in.setSuggestionWords(wordsSet);

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(in);
        tx.commit();
    }

    public void tagCleaner() {
        List<Tag> asList = Datastore.query(Tag.class).asList();
        for (Tag tag : asList) {
            List<TagGame> asList2 =
                Datastore
                    .query(TagGame.class)
                    .filter(TagGameMeta.get().tagRef.equal(tag.getKey()))
                    .asList();
            System.out.println(asList2.size());
            if (asList2.size() == 0) {
                GlobalTransaction tx = Datastore.beginGlobalTransaction();
                tx.delete(tag.getKey());
                tx.commit();
            }

        }
    }
}