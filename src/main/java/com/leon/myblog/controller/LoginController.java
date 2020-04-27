package com.leon.myblog.controller;

import com.leon.myblog.enity.User;
import com.leon.myblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @ author ：leon
 * @ date ：Created in 2019-11-21 17:03
 * @ description：${description}
 * @ modified By：
 * @ version: $version$
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestParam("username") String username,@RequestParam("password") String password) {

        Map<String, Object> resultMap = new HashMap<>();

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        ModelAndView mv = new ModelAndView();
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
            resultMap.put("code","511");
            resultMap.put("sessionId",sessionId);
            resultMap.put("message","登录成功");
            return resultMap;
        } else {
            token.clear();
            resultMap.put("code","512");
            resultMap.put("message","登录失败");
            return resultMap;
        }
    }

    @GetMapping("/loginpage")
    public ModelAndView loginpage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/login.html");
        // 母版
        return mv;
    }

}
