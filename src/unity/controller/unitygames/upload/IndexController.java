package unity.controller.unitygames.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.ProfileImage;
import twitter4j.Twitter;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Twitter twitter = (Twitter) sessionScope("twitter");
        
      
        if(twitter ==null){
          
            
            requestScope("userName", "Guest");
            requestScope("type", "guest");
//            requestScope(
//                "p",
//                "/images/face.png");
            
        }else{
            ProfileImage profileimage = null;
            // Twitter画像URL取得
            requestScope("userName", twitter.getScreenName());
            requestScope(
                "p",
                twitter.getProfileImage(
                    twitter.getScreenName(),
                    profileimage.MINI).getURL());
            requestScope("type", "twitter");
    
        
        }
        
        return forward("index.jsp");
    }
}
