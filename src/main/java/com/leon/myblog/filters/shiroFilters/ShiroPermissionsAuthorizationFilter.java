package com.leon.myblog.filters.shiroFilters;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ：leon
 * @date ：Created in 2020-03-19 12:45
 * @description：${description}   ShiroPermissionsAuthorizationFilter
 * @modified By：
 * @version: $version$
 */
public class ShiroPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        System.out.println("333");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code",407);
            jsonObject.put("msg","没有对应的权限！");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonObject.put("msg","没有权限！");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //System.out.println("110");
        out.println( jsonObject);
        out.flush();
        out.close();
        return false;
        //return super.onAccessDenied(request, response);
    }
}
