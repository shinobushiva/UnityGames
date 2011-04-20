package unity.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.model.GameData;

public class ViewController extends Controller {
    private GameDataMeta g = GameDataMeta.get();

    @Override
    public Navigation run() throws Exception {

        String data = asString("view");
        System.out.println("se:" + data);
        // 投稿日時が古い順
   
            if (data.equals("OldEntry")) {
                List<GameData> Game =
                    Datastore.query(g).sort(g.date.asc).asList();
                requestScope("GameList", Game);
                for (GameData game : Game) {
                    long l = game.getDate().getTime() + 1000 * 60 * 60 * 9;
                    game.getDate().setTime(l);

                    if (game.getContents().length() >= 80) {
                        String s = game.getContents().substring(0, 80);
                        game.setContents(s + "...");
                    }
                    if (game.getOperations().length() >= 80) {
                        String o = game.getOperations().substring(0, 80);
                        game.setOperations(o + "...");
                    }

                }
            }
            // アクセス数が多い順
            else if (data.equals("MostAccess")) {
                List<GameData> Game =
                    Datastore.query(g).sort(g.access.desc).asList();
                requestScope("GameList", Game);
                for (GameData game : Game) {
                    long l = game.getDate().getTime() + 1000 * 60 * 60 * 9;
                    game.getDate().setTime(l);

                    if (game.getContents().length() >= 80) {
                        String s = game.getContents().substring(0, 80);
                        game.setContents(s + "...");
                    }
                    if (game.getOperations().length() >= 80) {
                        String o = game.getOperations().substring(0, 80);
                        game.setOperations(o + "...");
                    }

                }
            }
            // アクセス数が少ない順
            else if (data.equals("LeastAccess")) {
                List<GameData> Game =
                    Datastore.query(g).sort(g.access.asc).asList();
                requestScope("GameList", Game);
                for (GameData game : Game) {
                    long l = game.getDate().getTime() + 1000 * 60 * 60 * 9;
                    game.getDate().setTime(l);

                    if (game.getContents().length() >= 80) {
                        String s = game.getContents().substring(0, 80);
                        game.setContents(s + "...");
                    }
                    if (game.getOperations().length() >= 80) {
                        String o = game.getOperations().substring(0, 80);
                        game.setOperations(o + "...");
                    }

                }
            }
            // コメントが多い順
            else if (data.equals("MostComment")) {
                List<GameData> Game =
                    Datastore.query(g).sort(g.comment.desc).asList();
                requestScope("GameList", Game);
                for (GameData game : Game) {
                    long l = game.getDate().getTime() + 1000 * 60 * 60 * 9;
                    game.getDate().setTime(l);

                    if (game.getContents().length() >= 80) {
                        String s = game.getContents().substring(0, 80);
                        game.setContents(s + "...");
                    }
                    if (game.getOperations().length() >= 80) {
                        String o = game.getOperations().substring(0, 80);
                        game.setOperations(o + "...");
                    }

                }
            }
            // コメントが少ない順
            else if (data.equals("LeastComment")) {
                List<GameData> Game =
                    Datastore.query(g).sort(g.comment.asc).asList();
                requestScope("GameList", Game);
                for (GameData game : Game) {
                    long l = game.getDate().getTime() + 1000 * 60 * 60 * 9;
                    game.getDate().setTime(l);

                    if (game.getContents().length() >= 80) {
                        String s = game.getContents().substring(0, 80);
                        game.setContents(s + "...");
                    }
                    if (game.getOperations().length() >= 80) {
                        String o = game.getOperations().substring(0, 80);
                        game.setOperations(o + "...");
                    }

                }
            }
            // 投稿日時が新しい順&デフォルト
            else {
                System.out.println("koko");
                List<GameData> Game =
                    Datastore.query(g).sort(g.date.desc).asList();
                requestScope("GameList", Game);
                for (GameData game : Game) {
                    long l = game.getDate().getTime() + 1000 * 60 * 60 * 9;
                    game.getDate().setTime(l);

                    if (game.getContents().length() >= 80) {
                        String s = game.getContents().substring(0, 80);
                        game.setContents(s + "...");
                    }
                    if (game.getOperations().length() >= 80) {
                        String o = game.getOperations().substring(0, 80);
                        game.setOperations(o + "...");
                    }

                }
            }
        
        return forward("view.jsp");
    }
}
