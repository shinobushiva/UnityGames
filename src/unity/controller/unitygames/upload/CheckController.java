package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;


public class CheckController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        requestScope("GameName");
        requestScope("ThumbNailURL");
     FileItem GameFile =   requestScope("GameFile"); 
     FileItem ThumbNail =   requestScope("ThumbNail");
        requestScope("Contents");
        requestScope("Operations");
        
        requestScope("GameFiles", GameFile);
        requestScope("ThumbNails",ThumbNail);
        return forward("check.jsp");
    }
}
