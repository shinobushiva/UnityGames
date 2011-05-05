package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import twitter4j.Twitter;
import unity.service.ChangeService;

public class UploadController extends Controller {
    private ChangeService service = new ChangeService();

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

        Twitter twitter = (Twitter) sessionScope("twitter");
        long twitterId = 0;
        if (twitter != null) {
            twitterId = twitter.getId();
            System.out.println("pass:" + pass.isEmpty());
        }

        System.out.println("okかな？"+twitterId);
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
            twitterId);
        return forward("uploaded.jsp");
    }
}
