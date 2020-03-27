package com.leon.myblog.filters.shiroFilters;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * @author ：leon
 * @date ：Created in 2020-03-25 17:05
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class ShiroLogoutFilter  extends LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        if (this.isPostOnlyLogout() && !WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
            return this.onLogoutRequestNotAPost(request, response);
        } else {
            //String redirectUrl = this.getRedirectUrl(request, response, subject);

            try {
                subject.logout();
            } catch (SessionException var6) {
                //log.debug("Encountered session exception during logout.  This can generally safely be ignored.", var6);
            }

            //this.issueRedirect(request, response, redirectUrl);
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setStatus(200);
            //resp.setHeader("Access-Control-Allow-Origin",  req.getHeader("Origin"));
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setContentType("application/json; charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",4033);
            jsonObject.put("msg","登出");
            out.write( jsonObject.toString());
            out.flush();
            out.close();
            return false;
        }
    }
}
