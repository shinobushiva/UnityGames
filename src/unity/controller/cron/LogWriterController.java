package unity.controller.cron;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.LogTextMeta;
import unity.model.Log;
import unity.model.LogText;
import unity.service.CronService;

public class LogWriterController extends Controller {
    private CronService cs = new CronService();

    @Override
    public Navigation run() throws Exception {

        // こっから
        long start = System.currentTimeMillis();
        List<Log> setLog = Datastore.query(Log.class).limit(100).asList();
        for (Log log : setLog) {
            // dateをformat
            Date format = cs.format(log.getDate());
            // これを基準に判別する。ファイル名もこれ
            SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd");
            String fileName = mmdd.format(format);
            // こっからLogtext作成 or 更新
            LogText asSingle = null;
            try {
                asSingle =
                    Datastore
                        .query(LogText.class)
                        .filter(LogTextMeta.get().fileName.equal(fileName))
                        .asSingle();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (asSingle != null) {
                // 既に作成されていたら加える
                // System.out.println("更新");
                // こっからフラグメント作成
                int length = cs.createFragment(log, asSingle);
                // ここまでフラグメント作成
                asSingle.setKey(asSingle.getKey());
                // asSingle.setLog(bytes);
                // まとめてのlength
                asSingle.setLength(length);
                cs.save(asSingle);
            } else {
                // なかったら作成
                // System.out.println("新規");
                LogText logText = new LogText();
                logText.setFileName(fileName);
                // こっからフラグメント作成
                int length = cs.createFragment(log, logText);
                // ここまでフラグメント作成
                logText.setKey(Datastore.allocateId(LogText.class));
                logText.setLength(length);
                cs.save(logText);
            }
            // 最後にLog削除
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.delete(log.getKey());
            tx.commit();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
        // // ここまで

        return null;
    }
}
