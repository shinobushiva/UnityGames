package unity.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.MovieRankingMeta;
import unity.meta.UploadedDataFragmentMeta;
import unity.model.Log;
import unity.model.LogText;
import unity.model.MovieRanking;
import unity.model.UploadedDataFragment;

public class CronService {
    private static final int FRAGMENT_SIZE = 900000;

    public List<MovieRanking> movieRanking() {

        List<MovieRanking> asList =
            Datastore
                .query(MovieRanking.class)
                .sort(MovieRankingMeta.get().point.asc)
                .asList();
        return asList;
    }

    public void save(Object o) {

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(o);
        tx.commit();
    }

    public Date format(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("JST");
        TimeZone.setDefault(timeZone);
        return date;
    }

    public int createFragment(Log log, LogText logText) {
        UploadedDataFragmentMeta fm = UploadedDataFragmentMeta.get();
        int length = 0;
        List<UploadedDataFragment> fragmentList =
            Datastore
                .query(UploadedDataFragment.class)
                .filter(fm.type.equal("LogText"))
                .filter(fm.uploadDataRef3.equal(logText.getKey()))
                .asList();
        if (fragmentList.size() != 0) {
            int count = 0;
            for (UploadedDataFragment f : fragmentList) {
                if (f.getLength() < FRAGMENT_SIZE) {
                    // 保存できる許容範囲内のエンティティがある場合
                    StringBuilder sb =
                        new StringBuilder(byteToString(f.getBytes()));
                    sb.append(appendString(log));
                    byte[] stringToByte = stringToByte(sb.toString());
                    f.setBytes(stringToByte);
                    f.setLength(stringToByte.length);
                    save(f);
                    count++;
                    // System.out.println("FragmentRefresh");
                }
                length += f.getLength();
            }
            if (count == 0) {
                // 保存できる許容範囲内のエンティティがない場合
                // 新たにエンティティ作成
                byte[] stringToByte = stringToByte(appendString(log));
                UploadedDataFragment ff = new UploadedDataFragment();
                ff.setKey(Datastore.allocateId(UploadedDataFragment.class));
                ff.setBytes(stringToByte);
                ff.setIndex(fragmentList.size());
                ff.setLength(stringToByte.length);
                ff.setType("LogText");
                ff.getUploadDataRef3().setModel(logText);
                save(ff);
                count = 0;
                System.out.println("createFragmentNew");
            }
        } else {
            // fragmentが１つも存在しないとき
            byte[] stringToByte = stringToByte(appendString(log));
            UploadedDataFragment ff = new UploadedDataFragment();
            ff.setKey(Datastore.allocateId(UploadedDataFragment.class));
            ff.setBytes(stringToByte);
            ff.setIndex(0);
            ff.setLength(stringToByte.length);
            ff.setType("LogText");
            ff.getUploadDataRef3().setModel(logText);
            save(ff);
            length += stringToByte.length;
            System.out.println("fragmentが１つも存在しないとき createFragmentNew");
        }
        return length;
    }

    public String byteToString(byte[] bytes) {
        String string = "";
        try {
            string = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    public byte[] stringToByte(String str) {
        byte[] bytes = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public String appendString(Log log) {
        return log.getHeaderMap()
            + " -/- "
            + log.getDate()
            + " -/- "
            + log.getUserId()
            + "¥n";
    }
}
