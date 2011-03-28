package unity.controller.unitygames;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GameControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/unitygames/Game");
        GameController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/unitygames/Game.jsp"));
    }
}
