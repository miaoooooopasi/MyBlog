package com.leon.myblog.controller;/*
 *@创建人：leon
 *@创建时间： 2019/11/11
 *@描述：
 */

import com.leon.myblog.enity.User;
import com.leon.myblog.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("更加用户名查询用户信息信息")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("get")
    public User select(@RequestParam("username") String username){
        return userService.findByUserName(username);
    }

    @PostMapping("/signup")
    public int signuoUser(){
        User user=new User();
        user.setAvatarimgurl("cs");
        user.setEmail("1429169422@qq.com");
        user.setGender("man");
        user.setPhone("15228390982");
        user.setPwd("leon");
        user.setUsername("leon");
        user.setRoleid(1);
        return userService.insertUser(user);
    }
}
