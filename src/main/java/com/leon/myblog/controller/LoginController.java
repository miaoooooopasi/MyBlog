package com.leon.myblog.controller;

import com.leon.myblog.enity.User;
import com.leon.myblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ author ：leon
 * @ date ：Created in 2019-11-21 17:03
 * @ description：${description}
 * @ modified By：
 * @ version: $version$
 */

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    //private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/login")
    //@CrossOrigin(origins = "http://localhost:8080")
    public Map<String,Object> login(@RequestParam String username, @RequestParam String password) {

        Map<String, Object> resultMap = new HashMap<>();

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

       System.out.println("登录用户是："+username+"token:"+token);
        User user=userService.findByUserName(username);

        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            resultMap.put("code","510");
            resultMap.put("message","未知账户");
            return resultMap;
            //return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            resultMap.put("code","509");
            resultMap.put("message","密码不正确");
            return resultMap;
            //return "密码不正确";
        } catch (LockedAccountException lae) {
            resultMap.put("code","508");
            resultMap.put("message","账户已锁定");
            return resultMap;
           // return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            resultMap.put("code","506");
            resultMap.put("message","用户名或密码错误次数过多");
            return resultMap;
            //return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            resultMap.put("code","507");
            resultMap.put("message","用户名或密码不正确");
            return resultMap;
            //return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            String sessionId = (String)subject.getSession().getId();
            resultMap.put("user",user);
            resultMap.put("code",200);
            resultMap.put("TOKEN",sessionId);
            resultMap.put("message","登录成功");
            return resultMap;
        } else {
            token.clear();
            resultMap.put("code","512");
            resultMap.put("message","登录失败");
            return resultMap;
        }
    }




}
