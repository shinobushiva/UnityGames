package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import unity.service.UploadService;

public class UploadController extends Controller {
    private UploadService service = new UploadService();
    
    @Override
    public Navigation run() throws Exception {
        
       String GameName = requestScope("GameName");
       String ThumbNailURL = requestScope("ThumbNailURL");
       FileItem GameFile = requestScope("GameFile"); 
       FileItem ThumbNail = requestScope("ThumbNail");
       String Contents = requestScope("Contents");
       String Operations = requestScope("Operations");
       String HpURL = requestScope("HpURL");
       String GameURL = requestScope("GameURL");
       String Pass = requestScope("pass");
       String ThumbNailType = requestScope("ThumbNailType");
       String GameType = requestScope("GameType");
       String fixTag = requestScope("fixTag");
       String code = requestScope("Code");
       
       service.upload(GameName,GameURL,GameFile,ThumbNail,ThumbNailURL,Contents,Operations,HpURL,Pass,ThumbNailType,GameType,fixTag,code);
       
           

       
       
       
       
      
       
       
       
        
        return forward("uploaded.jsp");
    }
}
