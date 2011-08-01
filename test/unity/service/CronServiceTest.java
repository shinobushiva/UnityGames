package unity.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.tester.AppEngineTestCase;

import unity.model.Log;

public class CronServiceTest extends AppEngineTestCase {

    public List<Log> setLog() {
        List<Log> list = new ArrayList<Log>();
        for (int i = 1; i < 30; i++) {
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("servletPath", "servletPath");
            headerMap.put("RemoteAddr", "RemoteAddr");
            headerMap.put("RemoteHost", "RemoteHost");
            headerMap.put("RemotePort", "RemotePort");
            headerMap.put("RemoteUser", "RemoteUser");
            headerMap.put("RequestURI", "RequestURI");
            headerMap.put("RequestURL", "RequestURL");

            Log l = new Log();
            l.setDate(createDate(i));
            l.setHeaderMap(headerMap);
            l.setUserId("kyusyukeigo");
            list.add(l);
            GlobalTransaction tx = Datastore.beginGlobalTransaction();
            tx.put(l);
            tx.commit();
        }
        return list;
    }

    public Date createDate(int i) {
        String date = "2011/07/" + i;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        TimeZone.setDefault(timeZone);
        Date parse = null;
        try {
            parse = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public Date format(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("JST");
        TimeZone.setDefault(timeZone);
        return date;
    }
}
