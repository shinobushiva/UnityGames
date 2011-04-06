package unity.controller.unitygames;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import unity.service.ChangeService;

public class ChangeUpController extends Controller {

    private ChangeService service = new ChangeService();
    @Override
    public Navigation run() throws Exception {
        
        String k = requestScope("key");
        Key key = KeyFactory.stringToKey(k);
        String GameName = requestScope("GameName");
        String ThumbNailURL = requestScope("ThumbNailURL");
        FileItem GameFile = requestScope("GameFile"); 
        FileItem ThumbNail = requestScope("ThumbNail");
        String Contents = requestScope("Contents");
        String Operations = requestScope("Operations");
        String HpURL = requestScope("HpURL");
        String GameURL = requestScope("GameURL");
        String Pass = requestScope("pass");
        String ThumbNailType =requestScope("ThumbNailType");
        String GameType =requestScope("GameType");
        String ThumbNailChange =requestScope("ThumbNailChange");
        String GameChange =requestScope("GameChange");
        String fixTag = requestScope("fixTag");
        String code = requestScope("Code");
        String delete = requestScope("delete");
        
        
//        String[] tag = fixTag.split(",");
//       
//        if(tag.length >= 4){
//            System.out.println("エラーです");
//        
//            
//            return forward("change");
//        }
        
        service.change(key,GameName,GameURL,GameFile,ThumbNail,ThumbNailURL,Contents,Operations,HpURL,Pass,ThumbNailType,GameType,ThumbNailChange,GameChange,fixTag,code);
        
        
        
        
        return forward("changed.jsp") ;
    }
}
