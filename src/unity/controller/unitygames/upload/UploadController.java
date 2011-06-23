package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.util.BeanUtil;

import unity.model.GameData;
import unity.model.SessionGameData;
import unity.service.ChangeService;
import unity.service.TagService;

public class UploadController extends Controller {
    private ChangeService service = new ChangeService();
    private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

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
        String gameType = requestScope("GameType");
        String fixTag = requestScope("fixTag");
        String code = requestScope("Code");
        boolean editCode = Boolean.valueOf((String) requestScope("editCode"));

        String gameScreenSize =
            requestScope("gameScreenWidth")
                + ","
                + requestScope("gameScreenHeight");

        // Twitterで管理するなら
        if (pass.isEmpty()) {

            SessionGameData sg = new SessionGameData();
            BeanUtil.copy(request, sg);
            sg.setKey(Datastore.allocateId(SessionGameData.class));
            sg.setGameScreenSize(gameScreenSize);
            sg.setFixTags(fixTag);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(sg);
            tx.commit();

            sessionScope("sessionKey", sg.getKey());
            if (thumbNail != null)
                sessionScope("thumbNail", thumbNail.getData());

            if (gameFile != null)
                sessionScope("gameFile", gameFile.getData());

            // Callback用
            sessionScope("loginType", "newGame");

            // return null;
            return forward("/login/oAuth");
        }

        GameData g =
            service.change(
                null,
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
                "",
                "",
                fixTag,
                code,
                0,
                gameScreenSize,
                editCode);

        // 二度手間してるけど力尽きたので放置・・・6/14
        ts.conflictTag(g.getKey());
        ts.setRelation(g.getKey());
        ts.deleteRelationTag(g.getKey());

        return redirect("/unitygames/game/ug" + g.getKey().getId());
    }
}
