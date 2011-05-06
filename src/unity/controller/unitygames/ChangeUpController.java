package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import twitter4j.Twitter;
import unity.service.ChangeService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class ChangeUpController extends Controller {

    private ChangeService service = new ChangeService();

    @Override
    public Navigation run() throws Exception {

        String k = requestScope("key");
        Key key = KeyFactory.stringToKey(k);
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

        String thumbNailChange = requestScope("ThumbNailChange");
        String gameChange = requestScope("GameChange");

        String fixTag = requestScope("fixTag");
        String code = requestScope("Code");

        Twitter twitter = (Twitter) sessionScope("twitter");
        long twitterId = 0;
        if(twitter !=null){
        
        twitterId = twitter.getId();
        }
        service.change(
            key,
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
            twitterId);

        return forward("changed.jsp");
    }
}
