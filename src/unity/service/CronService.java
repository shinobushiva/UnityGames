package unity.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.meta.MovieRankingMeta;
import unity.model.Log;
import unity.model.LogText;
import unity.model.MovieRanking;

public class CronService {

    public List<MovieRanking> movieRanking() {

        List<MovieRanking> asList =
            Datastore
                .query(MovieRanking.class)
                .sort(MovieRankingMeta.get().point.asc)
                .asList();
        return asList;
    }

    public void setLogText(List<Log> logs) {
        LogText logText = new LogText();
        String strArray = "";
        for (Log log2 : logs) {
            strArray +=
                log2.getHeaderMap()
                    + " / "
                    + log2.getDate()
                    + " / "
                    + log2.getUserId()
                    + "\n";
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.delete(log2.getKey());
            tx.commit();
        }
        try {
            byte[] bytes = strArray.getBytes("UTF-8");
            logText.setLog(bytes);
            logText.setDate(new Date());
            logText.setLength(bytes.length);
            save(logText);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void save(Object o) {

        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        Datastore.put(o);
        tx.commit();
    }

    public void setLog(int count) {

        for (int i = 0; i < count; i++) {
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("count", "" + i);
            headerMap.put("servletPath", "servletPath");
            headerMap.put("RemoteAddr", "RemoteAddr");
            headerMap.put("RemoteHost", "RemoteHost");
            headerMap.put("RemotePort", "RemotePort");
            headerMap.put("RemoteUser", "RemoteUser");
            headerMap.put("RequestURI", "RequestURI");
            headerMap.put("RequestURL", "RequestURL");

            Log l = new Log();
            l.setDate(new Date());
            l.setHeaderMap(headerMap);
            l.setUserId("kyusyukeigo");

            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(l);
            tx.commit();
        }
    }
}
