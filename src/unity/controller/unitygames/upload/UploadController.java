package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;

import unity.meta.GameDataMeta;
import unity.meta.ThumbNailDataMeta;
import unity.model.GameData;
import unity.model.ThumbNailData;
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
       
       
       service.upload(GameName,GameURL,GameFile,ThumbNail,ThumbNailURL,Contents,Operations,HpURL,Pass);
       
           

       
       
       
       
      
       
       
       
        
        return forward("uploaded.jsp");
    }
}
