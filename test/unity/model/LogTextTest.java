package unity.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LogTextTest extends AppEngineTestCase {

    private LogText model = new LogText();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
