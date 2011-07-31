package unity.controller.cron;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import com.google.appengine.api.datastore.Key;

import unity.model.Log;
import unity.model.LogText;
import unity.service.CronService;
import unity.service.CronServiceTest;

public class LogWriterController extends Controller {
    private CronService cs = new CronService();

    @Override
    public Navigation run() throws Exception {
//        GlobalTransaction tx;
//        List<Key> asKeyList = Datastore.query(LogText.class).asKeyList();
//        for (Key key : asKeyList) {
//            tx = Datastore.beginGlobalTransaction();
//            tx.delete(key);
//            tx.commit();
//        }
//        tx = Datastore.beginGlobalTransaction();
//        List<Key> asKeyList2 = Datastore.query(Log.class).asKeyList();
//        Datastore.delete(asKeyList2);
//        tx.commit();
        // Log作成
//         cs.setLog(5000);
        // ここから
        // limitつけるのは最初だけ
        // 1日ごとに全Logを1まとめにする
        //１回で取得するのは5000くらい？
        //5000はダメだったので1000 を1時間ごとにする
         long start = System.currentTimeMillis();
         List<Log> asList = Datastore.query(Log.class).limit(1000).asList();
         cs.setLogText(asList);
         long end = System.currentTimeMillis();
         System.out.println("Time : " + (end - start));
        // ここまで

        return null;
    }
}
