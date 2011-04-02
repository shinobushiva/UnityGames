package unity.controller;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import unity.controller.ajax.TagUploadController;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TagUploadControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/tagUpload");
        TagUploadController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
