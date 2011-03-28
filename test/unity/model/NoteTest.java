package unity.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class NoteTest extends AppEngineTestCase {

    private Note model = new Note();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
