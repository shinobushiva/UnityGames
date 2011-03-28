package unity.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UploadedDataTest extends AppEngineTestCase {

    private GameData model = new GameData();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
