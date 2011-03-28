package unity.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UploadServiceTest extends AppEngineTestCase {

    private UploadService service = new UploadService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
