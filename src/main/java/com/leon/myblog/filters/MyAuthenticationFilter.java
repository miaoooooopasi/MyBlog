package com.leon.myblog.filters;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：leon
 * @date ：Created in 2020-02-10 15:41
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class MyAuthenticationFilter extends FormAuthenticationFilter {


    //private static final Logger logger = LoggerFactory.getLogger(CORSAuthenticationFilter.class);

    public CORSAuthenticationFilter() {
        super();
    }

isAccessAllowed(request, response, mappedValue);
    return super.
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONSif (request instanceof HttpServletRequest) {
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
    }
}

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        Map<String, Object> map= new HashMap<>();
        map.put("code", 702);
        map.put("msg", "未登录");
        writer.write(JSON.toJSONString(map));
        writer.close();
        return false;
    }

}

