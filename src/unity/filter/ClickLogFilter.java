package unity.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import unity.model.Log;

public class ClickLogFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
            throws IOException, ServletException {
        // TODO Auto-generated method stub

        HttpServletRequest hsr = (HttpServletRequest) req;

        @SuppressWarnings("unused")
        StringBuffer requestURL = hsr.getRequestURL();
        Date date = new Date();
        @SuppressWarnings("unused")
        String remoteHost = hsr.getRemoteHost();

        Map<String, String> headerMap = new HashMap<String, String>();
        @SuppressWarnings("rawtypes")
        Enumeration headerNames = hsr.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String hn = (String) headerNames.nextElement();
            String header = hsr.getHeader(hn);
            headerMap.put(hn, header);
        }

        System.out.println(headerMap);

        Log l = new Log();
        l.setDate(date);
        l.setHeaderMap(headerMap);
        l.setUserId((String) hsr.getSession().getAttribute("loginUser"));
        
        GlobalTransaction tx = Datastore.beginGlobalTransaction();
        tx.put(l);
        tx.commit();

        fc.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}