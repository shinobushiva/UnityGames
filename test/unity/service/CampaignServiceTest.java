package unity.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CampaignServiceTest extends AppEngineTestCase {

    private CampaignService service = new CampaignService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
