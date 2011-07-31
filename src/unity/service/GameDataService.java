package unity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.EveryDayGameRankingMeta;
import unity.meta.GameDataMeta;
import unity.model.CommentaryLog;
import unity.model.EveryDayGameRanking;
import unity.model.GameData;
import unity.model.Tag;
import unity.model.TagGame;
import unity.model.User;
import unity.model.api.Game;
import unity.model.vo.EveryDayGameRankingVo;
import unity.utils.CodeBlockUtils;

import com.google.appengine.api.datastore.Key;

public class GameDataService {
    public static final int PRIVATE_BEST = 2;
    public static final int PRIVATE_BETTER = 1;
    public static final int PUBLIC_GAME = 0;

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

    public GameData getGameData(Long id) {
        return Datastore.get(
            GameData.class,
            Datastore.createKey(GameData.class, id));
    }

    public List<GameData> getAll() {
        return Datastore.query(GameData.class).asList();
    }

    public void addPoint(GameData g) {
        int point = g.getAccess() + g.getComment() * 3;
        g.setPoint(point);
        save(g);
    }

    public void addAccessPoint(GameData g) {
        g.setAccess(g.getAccess() + 1);
        save(g);
    }

    public String toCodeJson(String str) {
        return CodeBlockUtils.toCodeJson(str);

    }

    public CommentaryLog commentaryLog(Key gameKey, String commentary) {

        CommentaryLog cl = new CommentaryLog();
        cl.setKey(Datastore.allocateId(CommentaryLog.class));
        cl.setGameKey(gameKey);
        cl.setCommentary(commentary);
        cl.setDate(new Date());

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(cl);
        tx.commit();

        return null;
    }

    public Game getGameApi(Key key) {

        return Datastore.get(Game.class, key);
    }

    public List<GameData> newGame() {
        return Datastore
            .query(GameData.class)
            .filter(GameDataMeta.get().accessLevel.equal(PUBLIC_GAME))
            .sort(GameDataMeta.get().date.desc)
            .limit(5)
            .asList();
    }

    public List<EveryDayGameRankingVo> rankingGame(int maxNum) {

        List<EveryDayGameRanking> asList =
            Datastore
                .query(EveryDayGameRanking.class)
                .sort(EveryDayGameRankingMeta.get().deltaPoint.desc)
                .asList();
        int size = asList.size();
        if (size < maxNum)
            maxNum = size;
        List<EveryDayGameRankingVo> g = new ArrayList<EveryDayGameRankingVo>();
        for (EveryDayGameRanking edg : asList) {
            GameData gameData =
                Datastore.get(GameData.class, edg.getKey().getParent());
            contentCut(gameData);
            if (g.size() < maxNum) {

                EveryDayGameRankingVo setEdgvo = null;
                Set<Tag> fixTags = gameData.getFixTags();
                int deltaPoit = edg.getDeltaPoint();
                Boolean check = false;
                if (fixTags.size() != 0) {
                    for (Tag tag : fixTags) {
                        if (tag.getName().equals("チュートリアル")) {
                            check = true;
                        }
                    }
                    if (!check) {
                        setEdgvo = setEdgvo(gameData, deltaPoit);
                        g.add(setEdgvo);
                    }
                } else {
                    setEdgvo = setEdgvo(gameData, deltaPoit);
                    g.add(setEdgvo);
                }
            } else
                break;
        }
        return g;
    }

    public EveryDayGameRankingVo setEdgvo(GameData gamedata, int deltaPoint) {

        EveryDayGameRankingVo edgvo = new EveryDayGameRankingVo();
        edgvo.setDeltaPoint(deltaPoint);
        edgvo.getGameRef().setModel(gamedata);

        return edgvo;

    }

    public List<GameData> contentCut(List<GameData> gameList) {
        for (GameData game : gameList) {
            if (game.getContents().length() >= 80) {
                String s = game.getContents().substring(0, 80);
                game.setContents(s + "...");
            }
            if (game.getOperations().length() >= 80) {
                String o = game.getOperations().substring(0, 80);
                game.setOperations(o + "...");
            }
        }
        return gameList;
    }

    public GameData contentCut(GameData game) {

        if (game.getContents().length() >= 80) {
            String s = game.getContents().substring(0, 80);
            game.setContents(s + "...");
        }
        if (game.getOperations().length() >= 80) {
            String o = game.getOperations().substring(0, 80);
            game.setOperations(o + "...");
        }
        return game;
    }

    public void deleteTagGame(GameData g, Tag t) {
        g.getTags().remove(t);
        save(g);
    }

    public void createTags(GameData g) {
        g.setTags(new HashSet<Tag>());
        save(g);
    }

    public void addTag(GameData g, Tag t) {
        g.getTags().add(t);
        save(g);
    }

    public List<GameData> getTutorialGame(List<TagGame> tgl) {

        List<GameData> gds = new ArrayList<GameData>();
        for (TagGame gameData : tgl) {
            gds.add(gameData.getGameRef().getModel());
        }

        return gds;

    }

    public List<GameData> getViewPattern(int data) {
        List<GameData> game = null;
        GameDataMeta g = GameDataMeta.get();
        switch (data) {

        case 1:
            game =
                Datastore
                    .query(GameData.class)
                    .filter(g.accessLevel.equal(PUBLIC_GAME))
                    .sort(g.date.asc)
                    .limit(50)
                    .asList();
            break;
        case 2:
            game =
                Datastore
                    .query(GameData.class)
                    .filter(g.accessLevel.equal(PUBLIC_GAME))
                    .sort(GameDataMeta.get().access.desc)
                    .limit(50)
                    .asList();
            break;
        case 3:
            game =
                Datastore
                    .query(GameData.class)
                    .filter(g.accessLevel.equal(PUBLIC_GAME))
                    .sort(GameDataMeta.get().access.asc)
                    .limit(50)
                    .asList();
            break;
        case 4:
            game =
                Datastore
                    .query(GameData.class)
                    .filter(g.accessLevel.equal(PUBLIC_GAME))
                    .sort(GameDataMeta.get().comment.desc)
                    .limit(50)
                    .asList();
            break;
        case 5:
            game =
                Datastore
                    .query(GameData.class)
                    .filter(g.accessLevel.equal(PUBLIC_GAME))
                    .sort(GameDataMeta.get().comment.asc)
                    .limit(50)
                    .asList();
            break;
        default:
            game =
                Datastore
                    .query(GameData.class)
                    .filter(g.accessLevel.equal(PUBLIC_GAME))
                    .sort(GameDataMeta.get().date.desc)
                    .limit(50)
                    .asList();
            break;
        }

        return game;
    }

    public void setCode(GameData g, String commentary) {
        g.setCode(commentary);
        save(g);
    }

    public void setUgLink(GameData g) {
        // ug形式の短縮リンク変換
        String t = g.getContents();
        String regex = "ug[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(t);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group());
        }

        for (String st : list) {

            // ugの後が数字でないものはリンク化行わない
            if (!st.matches("ug[^0-9]*")) {

                // ugを無くしてidだけを抽出 例）ug1234 → 1234
                String ug = st.replaceAll("ug", "");

                String s =
                    t.replaceAll(st, "<a href='http://unity-games.appspot.com/"
                        + "unitygames/game/ug"
                        + ug
                        + "'class='ugLink'>"
                        + st
                        + "</a>");
                // 繰り返し置換していく
                t = s;
            }

        }

        g.setContents(t);
    }

    public String connectFixTags(GameData g) {
        StringBuilder buf = new StringBuilder();
        for (Tag t : g.getFixTags()) {
            buf.insert(0, t.getName() + ",");
        }
        if (buf.length() > 0) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    public List<GameData> myGameList(User uk) {
        return Datastore
            .query(GameData.class)
            .filter(GameDataMeta.get().twitterUserKey.equal(uk.getKey()))
            .sort(GameDataMeta.get().date.desc)
            .asList();

    }

}
