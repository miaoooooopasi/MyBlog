package com.leon.myblog.filters.shiroFilters;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
}
