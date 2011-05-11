package unity.controller.user;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

        String name = asString("name");

        Twitter t = (Twitter) sessionScope("twitter");
//        System.out.println("誰が見てる？"+t.getScreenName());
        if (t.getScreenName().equals(name)) {
        // ページを見ている人が本人か他人かを判別
        //ローカルなのでこっちのifつかってる
//        if (name.equals("kyusyukeigo")) {
            
            requestScope("name", "myName");
        } else {
            requestScope("name", "otherName");
            requestScope("other", name);
        }

        return forward("index.jsp");
    }
}
