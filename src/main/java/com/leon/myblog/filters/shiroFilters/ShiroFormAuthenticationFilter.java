package com.leon.myblog.filters.shiroFilters;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ：leon
 * @date ：Created in 2020-03-19 12:21
 * @description： 重写onAccessDenied 实现shiro对未认证的页直接返回json 而不是重定向
 */
public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setStatus(200);
        //resp.setHeader("Access-Control-Allow-Origin",  req.getHeader("Origin"));
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setContentType("application/json; charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",403);
        jsonObject.put("msg","登录异常");
        out.write( jsonObject.toString());
        out.flush();
        out.close();
        return false;
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONS
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
         }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        if(req.getMethod().equals(RequestMethod.OPTIONS.name())){
            res.setStatus(200);
            return true;
        }
        return super.onPreHandle(request, response, mappedValue);
    }
}
