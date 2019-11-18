package com.leon.myblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ：leon
 * @date ：Created in 2019-11-15 9:04
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/base.html");
        // 母版
        return mv;
    }

}
