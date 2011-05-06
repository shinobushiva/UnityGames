package jp.co.topgate.controller;

import java.util.Map;
import java.util.logging.Logger;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.google.apphosting.api.DeadlineExceededException;
import com.google.apphosting.api.ApiProxy.CapabilityDisabledException;

public abstract class JsonController extends Controller {
    static final Logger LOG = Logger.getLogger(JsonController.class.getName());
    static final String STATUS = "status";
    static final String ERRCODE = "errorCode";
    static final String ERRMESSAGE = "errorMessage";
    static final String CANRETRY = "canRetry";
    static final String STATUS_OK = "OK";
    static final String STATUS_NG = "NG";

    abstract protected Map<String, Object> handle() throws Exception;

    @Override
    protected Navigation run() throws Exception {
        Map<String, Object> map = handle();
        if (map == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            throw new AssertionError("handle() must not be null.");
        }
        if (map.get(STATUS) == null) {
            map.put(STATUS, STATUS_OK);
        }

        LOG.info("" + map);

        // JSONP
        String callback = request.getParameter("callback");
        if (callback != null) {
            response.setContentType("text/javascript");
            response.setCharacterEncoding("utf-8");
            response
                .getWriter()
                .write(callback + "(" + JSON.encode(map) + ");");
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            JSON.encode(map, response.getOutputStream());
        }

        response.flushBuffer();
        return null;
    }

    @Override
    protected Navigation handleError(Throwable error) throws Throwable {
        Map<String, Object> map = Maps.newHashMap();
        String errorCode;
        String errorMessage;
        boolean canRetry = false;
        if (error instanceof CapabilityDisabledException) {
            errorCode = "READONLY";
            errorMessage = "AppEngineã®ã‚µãƒ¼ãƒ“ã‚¹ãŒèª­ã¿å–ã‚Šå°‚ç”¨ã§ã™";
        } else if (error instanceof DatastoreTimeoutException) {
            errorCode = "DSTIMEOUT";
            errorMessage = "ãƒ‡ãƒ¼ã‚¿ã‚¹ãƒˆã‚¢ãŒã‚¿ã‚¤ãƒ ã‚¢ã‚¦ãƒˆã—ã¾ã—ãŸã€‚";
            canRetry = true;
        } else if (error instanceof DatastoreFailureException) {
            errorCode = "DSFAILURE";
            errorMessage = "ãƒ‡ãƒ¼ã‚¿ã‚¹ãƒˆã‚¢ã®ã‚¢ã‚¯ã‚»ã‚¹ã«å¤±æ•—ã—ã¾ã—ãŸã€‚";
        } else if (error instanceof DeadlineExceededException) {
            errorCode = "DEE";
            errorMessage = "30ç§’ã‚’è¶…ãˆã¦ã‚‚å‡¦ç†ãŒçµ‚äº†ã—ã¾ã›ã‚“ã§ã—ãŸã€‚";
            canRetry = true;
        } else {
            errorCode = "UNKNOWN";
            errorMessage = "äºˆæœŸã›ã¬ã‚¨ãƒ©ãƒ¼ãŒç™ºç”Ÿã—ã¾ã—ãŸã€‚" + error.toString();
        }
        map.put(STATUS, STATUS_NG);
        map.put(ERRCODE, errorCode);
        map.put(ERRMESSAGE, errorMessage);
        map.put(CANRETRY, canRetry);
        JSON.encode(map, response.getOutputStream());
        response.flushBuffer();
        return null;
    }
}