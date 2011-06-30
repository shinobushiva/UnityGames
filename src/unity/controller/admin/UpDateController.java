package unity.controller.admin;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.Comment;

public class UpDateController extends Controller {

    // private TagService ts = new TagService();

    @Override
    public Navigation run() throws Exception {

        List<Comment> asList = Datastore.query(Comment.class).asList();
        for (Comment comment : asList) {

            comment.setTwitterId("");
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(comment);
            tx.commit();
        }

        return null;
    }
}
