package unity.controller;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndexControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/");
        IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.requestScope("campaigns"), is(notNullValue()));
        assertThat(tester.requestScope("rankingGameList"), is(notNullValue()));   
        assertThat(tester.requestScope("newGameList"), is(notNullValue())); 
        assertThat(tester.requestScope("words"), is(notNullValue()));
        assertThat(tester.requestScope("tags"), is(notNullValue()));
        assertThat(tester.requestScope("movie"), is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/index.jsp"));
    }
}
