package unity.controller.unitygames.upload;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CheckControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/unitygames/upload/check");
        CheckController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/unitygames/upload/check.jsp"));
    }
}
