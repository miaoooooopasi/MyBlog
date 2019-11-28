package com.leon.myblog.controller;

import com.leon.myblog.enity.User;
import com.leon.myblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ author ：leon
 * @ date ：Created in 2019-11-15 9:04
 * @ description：${description}
 * @ modified By：
 * @ version: $version$
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public ModelAndView home(){
        // 获取当前登录用户名
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();

        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        mv.addObject("user",user);
        mv.setViewName("/admin/home.html");
        // 母版
        return mv;
    }

    @GetMapping("/addArticle")
    public ModelAndView addArticle(){
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        System.out.println(user.getUsername());
        mv.addObject("user",user);
        mv.setViewName("/admin/addArticle.html");
        return mv;
    }

}
