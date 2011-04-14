package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import unity.service.ChangeService;

public class UploadController extends Controller {
    private ChangeService service = new ChangeService();

    @Override
    public Navigation run() throws Exception {

        String GameName = requestScope("GameName");

        String ThumbNailURL = requestScope("ThumbNailURL");
        FileItem ThumbNail = requestScope("ThumbNail");

        FileItem GameFile = requestScope("GameFile");
        String Contents = requestScope("Contents");
        String Operations = requestScope("Operations");
        String HpURL = requestScope("HpURL");
        String GameURL = requestScope("GameURL");
        String Pass = requestScope("pass");
        String ThumbNailType = requestScope("ThumbNailType");
        String GameType = requestScope("GameType");

        String fixTag = requestScope("fixTag");
        String code = requestScope("Code");

        service.change(
            null,
            GameName,
            GameURL,
            GameFile,
            ThumbNail,
            ThumbNailURL,
            Contents,
            Operations,
            HpURL,
            Pass,
            ThumbNailType,
            GameType,
            "",
            "",
            fixTag,
            code);
        return forward("uploaded.jsp");
    }
}
