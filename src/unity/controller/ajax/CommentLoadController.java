package unity.controller.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.topgate.controller.JsonController;
import unity.model.Comment;
import unity.model.GameData;
import unity.service.CommentService;
import unity.service.GameDataService;

public class CommentLoadController extends JsonController {
    private GameDataService gs = new GameDataService();
    private CommentService cms = new CommentService();

    @Override
    protected Map<String, Object> handle() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        GameData g = gs.getGameData(asLong("id"));

        // コメント表示
        List<Comment> comments = cms.getCommentsDesc(g.getKey());

        cms.setCommentUser(comments);

        map.put("comments", comments);
        return map;
    }
}
