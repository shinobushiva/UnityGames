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

        //Twitterで管理するなら
        if (requestScope("pass").toString().isEmpty()){
         
            sessionScope("gameName",requestScope("GameName"));
            sessionScope("thumbNailURL",requestScope("ThumbNailURL"));
            sessionScope("thumbNail",requestScope("ThumbNail"));
            sessionScope("gameFile",requestScope("GameFile"));
            sessionScope("contents",requestScope("Contents"));
            sessionScope("operations",requestScope("Operations"));
            sessionScope("hpURL", requestScope("HpURL"));
            sessionScope("gameURL", requestScope("GameURL"));
            sessionScope("thumbNailType",requestScope("ThumbNailType"));
            sessionScope("gameType", requestScope("GameType"));
            sessionScope("fixTag", requestScope("fixTag"));
            sessionScope("code", requestScope("Code"));
            
            //Callback用
            sessionScope("loginType", "newGame");
     
            
            return forward("/login/oAuth");
        }
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
