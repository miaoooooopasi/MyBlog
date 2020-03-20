package com.leon.myblog.filters.shiroFilters;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ：leon
 * @date ：Created in 2020-03-19 12:33
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class ShiroRolesAuthorizationFilter extends RolesAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        //System.out.println("进入role错误函数");
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        Subject subject = getSubject(request, response);
        JSONObject jsonObject=new JSONObject();
        //System.out.println("ROLE 错误！！！！！！！！！");
        // 没有认证,先返回未认证的json
        if (subject.getPrincipal() == null) {
            jsonObject.put("code",405);
            jsonObject.put("msg","未登录，请登录");

        } else {
            // 已认证但没有角色，返回为授权的json
            jsonObject.put("code",406);
            jsonObject.put("msg","该用户没有该角色权限！");
        }
        out.println(jsonObject); //jsonObject.toString()
        out.flush();
        out.close();
        return false;

        //return super.onAccessDenied(request, response, mappedValue);
    }
}
