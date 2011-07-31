package unity.controller.cron;

import java.util.List;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.tester.ControllerTestCase;

import com.google.appengine.repackaged.com.google.common.base.genfiles.ByteArray;

import unity.model.Log;
import unity.model.LogText;
import unity.service.CronService;
import unity.service.CronServiceTest;

public class LogWriterControllerTest extends ControllerTestCase {
    private CronService cs = new CronService();
    private CronServiceTest cst = new CronServiceTest();

    @Test
    public void run() throws Exception {

        cst.setLog(10000);
        // ここから
        // limitつけるのは最初だけ
        // 1日ごとに全Logを1まとめにする
        long start = System.currentTimeMillis();
        List<Log> asList = Datastore.query(Log.class).asList();
        cs.setLogText(asList);
        long end = System.currentTimeMillis();
        System.out.println("Time : " + (end - start));
        // ここまで

    }
}
