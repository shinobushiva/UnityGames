package unity.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;

public class LoginCheckFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
            throws IOException, ServletException {

        HttpServletRequest hsr = (HttpServletRequest) req;
        HttpServletResponse hres = (HttpServletResponse) res;
        Twitter twitter = (Twitter) hsr.getSession().getAttribute("twitter");
        if (req.toString().contains("/unitygames/upload/newGame/guest")
            || req.toString().contains("/unitygames/upload/change/guest")) {
            fc.doFilter(req, res);
            return;
        }
        if (twitter == null)
            hres.sendRedirect("/login/loginCheck");
        else
            hsr.getSession().setAttribute("loginType", "twitter");
        
        fc.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
