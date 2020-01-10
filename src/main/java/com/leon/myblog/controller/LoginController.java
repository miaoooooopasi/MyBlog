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
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
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
            mv.setViewName("admin/unauth.html");
            mv.addObject("code","510");
            mv.addObject("message","未知账户");
            return mv;
            //return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            mv.setViewName("admin/unauth.html");
            mv.addObject("code","509");
            mv.addObject("message","密码不正确");
            return mv;
            //return "密码不正确";
        } catch (LockedAccountException lae) {
            mv.setViewName("admin/unauth.html");
            mv.addObject("code","508");
            mv.addObject("message","账户已锁定");
            return mv;
           // return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            mv.setViewName("admin/unauth.html");
            mv.addObject("code","506");
            mv.addObject("message","用户名或密码错误次数过多");
            return mv;
            //return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            mv.setViewName("admin/unauth.html");
            mv.addObject("code","507");
            mv.addObject("message","用户名或密码不正确");
            return mv;
            //return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            mv.setViewName("admin/home.html");
            mv.addObject("user",user);
            mv.addObject("code","511");
            mv.addObject("message","登录成功");
            return mv;
        } else {
            token.clear();
            mv.setViewName("admin/login.html");
            mv.addObject("code","512");
            mv.addObject("message","登录失败");
            return mv;
            //return "登录失败";
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
