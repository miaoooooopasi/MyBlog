package com.leon.myblog.controller;

import com.alibaba.fastjson.JSONArray;
import com.leon.myblog.enity.Category;
import com.leon.myblog.service.CategoryService;
import com.leon.myblog.service.SendMailService;
import com.leon.myblog.service.TestService;
import com.leon.myblog.service.UserService;
import com.leon.myblog.utils.IpUtil;
import com.leon.myblog.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtil;

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public void test(){



    }

    @GetMapping("/tt")
    public List<Category> tt(HttpServletRequest REQ){


        String key="shiro:cache:com.leon.myblog.configs.CustomRealm.authorizationCache:leon1";
        //redisUtil.set(key,50,60);
        System.out.println("123123:"+redisUtil.get(key));
        String ip= IpUtil.getIpAddr(REQ);
        System.out.println("IP:"+ip);

        return  categoryService.getall();

    }

    @PostMapping("/testjson")
    public Object  testjson(@RequestBody JSONArray param){


        for(int i=0;i<param.size();i++){
            System.out.println(param.getJSONObject(0).getString("title"));
            System.out.println(param.getJSONObject(0).getString("url"));

        }

        return "success!";
    }
}
