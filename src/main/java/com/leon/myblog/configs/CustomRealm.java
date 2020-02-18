package com.leon.myblog.configs;

import com.leon.myblog.enity.User;
import com.leon.myblog.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：leon
 * @date ：Created in 2019-11-21 12:25
 * @description：${description}
 * @modified By：
 * @version: $version$
 */



public class CustomRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;


    /*
     * description:  权限配置
     * version: 1.0
     * date:
     * author: leon
     * params:

     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){


        //String username= (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
        //Set<String> stringSet = new HashSet<>();
        //System.out.println(principalCollection.getPrimaryPrincipal());
        String username  = (String) principalCollection.getPrimaryPrincipal();
        User userInfo = userService.findByUserName(username);
        //System.out.println(userInfo);

        for (Integer integer : userService.getRoleByUserId(userInfo.getId())){
            info.addRole(userService.getRoleByRileId(integer).getRolename());
            System.out.println("role:"+userService.getRoleByRileId(integer).getRolename());
            for (Integer id : userService.getPermisonsByUserId(integer)){
                System.out.println("permision:"+userService.getPermisonByPermisionId(id).getPermisionname());
                info.addStringPermission(userService.getPermisonByPermisionId(id).getPermisionname());
            }
        }
        System.out.println(info.getObjectPermissions());
        return info;

    }

    /*
     * description:  登陆验证
     * version: 1.0
     * date:
     * author: leon
     * params:

     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String)authenticationToken.getPrincipal();
        //System.out.println(username+"010");
        User userInfo = userService.findByUserName(username);
        //System.out.println(userInfo+"123");
        if(userInfo == null)
        {
            return null;
        }

        SimpleAuthenticationInfo infos;
        infos = new SimpleAuthenticationInfo(username,userInfo.getPwd(),getName());


        return infos;
    }
}
