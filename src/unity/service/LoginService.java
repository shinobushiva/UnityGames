package unity.service;

import javax.servlet.http.HttpServletRequest;

public class LoginService {

    public boolean isLogin(HttpServletRequest r) {

        return (getLoginUserName(r) != null);
    }

    public String getLoginUserName(HttpServletRequest r) {

        return (String)r.getSession().getAttribute("loginUser");
    }

}
