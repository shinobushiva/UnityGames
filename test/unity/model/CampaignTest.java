package unity.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CampaignTest extends AppEngineTestCase {

    private Campaign model = new Campaign();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
