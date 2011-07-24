package unity.controller.action;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.Key;

import unity.controller.action.UploadController;
import unity.model.GameData;
import unity.model.UploadedDataFragment;
import unity.service.UploadService;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UploadControllerTest extends ControllerTestCase {

    private UploadService us = new UploadService();

    @Test
    public void run() throws Exception {
        tester.start("/action/upload");
        UploadController controller = tester.getController();
        assertThat(controller, is(notNullValue()));

        Key key = null;
        String gameName = "ゲーム名";
        String gameURL = "";
        FileItem gameFile =
            new FileItem(gameName, gameName, Datastore
                .query(UploadedDataFragment.class)
                .limit(1)
                .asSingle()
                .getBytes());
        FileItem thumbNail =
            new FileItem(gameName, gameName, Datastore
                .query(UploadedDataFragment.class)
                .limit(1)
                .asSingle()
                .getBytes());
        String thumbNailURL = "";
        String contents = "ゲーム説明";
        String operations = "操作説明";
        String hpURL = "";
        String pass = "";
        String thumbNailType = "data";
        String gameType = "data";
        boolean thumbNailChange = false;
        boolean gameChange = false;
        String fixTag = "チュートリアル";
        String code = "解説";
        long twitterId = 0;
        String gameScreenSize = "600,450";
        boolean editCode = false;
        String imageType = "image/png";
        assertThat(us.upload(
            key,
            gameName,
            gameURL,
            gameFile,
            thumbNail,
            thumbNailURL,
            contents,
            operations,
            hpURL,
            pass,
            thumbNailType,
            gameType,
            thumbNailChange,
            gameChange,
            fixTag,
            code,
            twitterId,
            gameScreenSize,
            editCode,
            imageType), is(notNullValue()));
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is("/unitygames/upload.jsp"));
    }
}
