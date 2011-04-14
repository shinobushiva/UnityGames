package unity.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TagGameTest extends AppEngineTestCase {

    private TagGame model = new TagGame();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
