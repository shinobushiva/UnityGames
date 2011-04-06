package unity.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DeleteServiceTest extends AppEngineTestCase {

    private DeleteService service = new DeleteService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
