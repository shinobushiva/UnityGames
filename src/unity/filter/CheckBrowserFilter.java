package unity.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckBrowserFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
            throws IOException, ServletException {
        HttpServletResponse hres = (HttpServletResponse) res;
        String userAgent = ((HttpServletRequest) req).getHeader("User-Agent");
        boolean ie = isIE(userAgent);
        
        if(ie)
            hres.sendRedirect("/error/browserError");
        
        
        fc.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    public static boolean isIE(String sUserAgent) {
        Pattern pattern = Pattern.compile(".*((MSIE)+ [0-7]\\.[0-9]).*");
        Matcher matcher = pattern.matcher(sUserAgent);
        boolean bMatch = matcher.matches();
        return bMatch;
    }
}
