package unity.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.tester.AppEngineTestCase;

import unity.model.Log;

public class CronServiceTest extends AppEngineTestCase {

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
