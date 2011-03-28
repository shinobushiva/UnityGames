package unity.controller.unitygames;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ChangeControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/unitygames/change");
        ChangeController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/unitygames/change.jsp"));
    }
}
