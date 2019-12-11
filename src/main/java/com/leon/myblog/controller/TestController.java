package com.leon.myblog.controller;

import com.leon.myblog.enity.Article;
import com.leon.myblog.service.TestService;
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
    private TestService testService;

    @GetMapping("/test")
    public List<Article> test(){
        return testService.test();
    }
}
