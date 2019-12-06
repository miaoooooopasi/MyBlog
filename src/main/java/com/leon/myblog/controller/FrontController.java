package com.leon.myblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ：leon
 * @date ：Created in 2019-12-06 15:13
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RestController
@RequestMapping("/front")
public class FrontController {


    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("/front/home.html");
        return mv;
    }

}
