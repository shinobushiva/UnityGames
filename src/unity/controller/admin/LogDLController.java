package unity.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class LogDLController extends Controller {

    @Override
    protected Navigation run() throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        TimeZone timeZone = TimeZone.getTimeZone("JST");
        TimeZone.setDefault(timeZone);
        String logDate = (String) requestScope("logDate");
        Date parse = format.parse(logDate);
        
        System.out.println(parse);

        return null;
    }
}
