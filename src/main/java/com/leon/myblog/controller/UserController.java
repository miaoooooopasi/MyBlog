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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
