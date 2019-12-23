package com.leon.myblog.controller;

import com.leon.myblog.enity.Category;
import com.leon.myblog.service.CategoryService;
import com.leon.myblog.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：leon
 * @date ：Created in 2019-12-11 14:40
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RestController
@RequestMapping("/front")
public class TestController {

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/test")
    public void test(){

        String to="1429169422@qq.com";
        String subject="123";
        String content="content";

        sendMailService.sendSimpleMail(to, subject, content);

    }

    @GetMapping("/tt")
    public List<Category> tt(){
        return  categoryService.getall();
    }
}
