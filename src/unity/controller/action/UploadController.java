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
    private UploadService service = new UploadService();
    private TagService ts = new TagService();

    @SuppressWarnings("static-access")
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

//        // 画像拡張子チェック
//        Magic parser = new Magic();
//        // getMagicMatch accepts Files or byte[],
//        // which is nice if you want to test streams
//        MagicMatch match = parser.getMagicMatch((byte[])thumbNail.getData());
//        System.out.println(""+match.getType());

        GameData g =
            service.upload(
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
                editCode);

        // 二度手間してるけど力尽きたので放置・・・6/14
        ts.conflictTag(g.getKey());
        ts.setRelation(g.getKey());
        ts.deleteRelationTag(g.getKey());

        return redirect("/unitygames/game/ug" + g.getKey().getId());
    }
}
