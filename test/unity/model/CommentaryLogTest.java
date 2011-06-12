package unity.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CommentaryLogTest extends AppEngineTestCase {

    private CommentaryLog model = new CommentaryLog();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
