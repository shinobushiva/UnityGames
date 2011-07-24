package unity.controller.action;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import twitter4j.Twitter;
import unity.model.GameData;
import unity.service.TagService;
import unity.service.UploadService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class UploadController extends Controller {
    private UploadService us = new UploadService();
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        Key gameKey = null;
        if (requestScope("gameKey") != null)
            gameKey = KeyFactory.stringToKey((String) requestScope("gameKey"));
        String gameName = requestScope("GameName");
        String thumbNailURL = requestScope("ThumbNailURL");
        FileItem thumbNail = requestScope("ThumbNail");
        FileItem gameFile = requestScope("GameFile");
        String contents = requestScope("Contents");
        String operations = requestScope("Operations");
        String hpURL = requestScope("HpURL");
        String gameURL = requestScope("GameURL");
        String pass = requestScope("pass");
        String thumbNailType = requestScope("ThumbNailType");
        boolean thumbNailChange =
            Boolean.parseBoolean((String) requestScope("ThumbNailChange"));
        boolean gameChange =
            Boolean.parseBoolean((String) requestScope("GameChange"));
        String gameType = requestScope("GameType");
        String fixTag = requestScope("fixTag");
        String code = requestScope("Code");
        boolean editCode =
            Boolean.parseBoolean((String) requestScope("editCode"));

        String gameScreenSize =
            requestScope("gameScreenWidth")
                + ","
                + requestScope("gameScreenHeight");

        removeSessionScope("loginType");
        long twitterId = 0;
        Twitter twitter = (Twitter) sessionScope("twitter");
        if (twitter != null)
            twitterId = twitter.getId();

        String mimeType = "";
        // 画像拡張子チェック
        if (thumbNail != null) {
            try {
                Magic parser = new Magic();
                @SuppressWarnings("static-access")
                MagicMatch match =
                    parser.getMagicMatch((byte[]) thumbNail.getData());

                mimeType = match.getMimeType();

                if (!mimeType.equals("image/png"))
                    if (!mimeType.equals("image/gif"))
                        if (!mimeType.equals("image/jpeg"))
                            return forward(request.getHeader("REFERER"));

            } catch (NoClassDefFoundError e) {
                requestScope("GameName", gameName);
                requestScope("Code", code);
                requestScope("Contents", contents);
                requestScope("Operations", operations);
                requestScope("GameFile", gameFile);
                return redirect(request.getHeader("REFERER"));
            }
        }
        // ゲーム拡張子チェック
        if (gameFile != null) {
            String a = gameFile.getFileName();
            int num = a.lastIndexOf(".");
            String b = a.substring(num + 1);

            if (!"unity3d".equals(b))
                return redirect(request.getHeader("REFERER"));
        }

        GameData g =
            us.upload(
                gameKey,
                gameName,
                gameURL,
                gameFile,
                thumbNail,
                thumbNailURL,
                contents,
                operations,
                hpURL,
                pass,
                thumbNailType,
                gameType,
                thumbNailChange,
                gameChange,
                fixTag,
                code,
                twitterId,
                gameScreenSize,
                editCode,
                mimeType);

        // 二度手間してるけど力尽きたので放置・・・6/14
        ts.conflictTag(g.getKey());
        ts.setRelation(g.getKey());
        ts.deleteRelationTag(g.getKey());

        return redirect("/unitygames/game/ug" + g.getKey().getId());
    }
}
