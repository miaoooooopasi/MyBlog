package com.leon.myblog.configs;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义session管理
 * <br/>
 * 传统结构项目中，shiro从cookie中读取sessionId以此来维持会话，在前后端分离的项目中（也可在移动APP项目使用），
 * 我们选择在ajax的请求头中传递sessionId，因此需要重写shiro获取sessionId的方式。
 * 自定义MySessionManager类继承DefaultWebSessionManager类，重写getSessionId方法
 * @author ：leon
 * @date ：2020/02/11 10:52
 */

public class MySessionManager extends DefaultWebSessionManager {

   // private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String AUTHORIZATION = "Authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public MySessionManager() {
        super();
    }


    @Override
    public Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //前端ajax的headers中必须传入Authorization的值
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            System.out.println("sessionId="+id);
            return id;
        } else {
            //否则按默认规则从cookie取sessionId
            //logger.info("sessionID为"+id);
            return super.getSessionId(request, response);
        }
    }
}
