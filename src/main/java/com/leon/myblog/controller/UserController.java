package com.leon.myblog.controller;/*
 *@创建人：leon
 *@创建时间： 2019/11/11
 *@描述：
 */

import com.leon.myblog.enity.RoleHasPermisionKey;
import com.leon.myblog.enity.RoleHasUserKey;
import com.leon.myblog.enity.User;
import com.leon.myblog.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ApiOperation("根据用户ID查询对应的角色")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int")
    @GetMapping("/getRoleById")
    public List<RoleHasUserKey> getRoleById(@RequestParam("id") int id){
        return userService.getRoleByUserId(id);
    }

    @ApiOperation("根据角色ID查询对应的权限")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "int")
    @GetMapping("/getPermisonById")
    public  List<RoleHasPermisionKey> getPermisonsById(@RequestParam("id") int id){
        return userService.getPermisonsByUserId(id);
    }
}