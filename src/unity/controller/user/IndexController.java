package unity.controller.user;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

        String name = asString("name");

//        Twitter t = (Twitter) sessionScope("twitter");

        // ページを見ている人が本人か他人かを判別
        // if (t.getScreenName().equals(name)) {
        //ローカルなのでこっちのifつかってる
        if (!name.equals("kyusyukeigo")) {
            
            requestScope("name", "myName");
        } else {
            requestScope("name", "otherName");
            requestScope("other", name);
        }

        return forward("index.jsp");
    }
}
