package unity.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class commentTest extends AppEngineTestCase {

    private Comment model = new Comment();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
