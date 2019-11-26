package com.leon.myblog.controller;/*
 *@创建人：leon
 *@创建时间： 2019/11/11
 *@描述：
 */

import com.leon.myblog.enity.User;
import com.leon.myblog.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("更加用户名查询用户信息信息")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @RequiresPermissions("1010")
    @GetMapping("/get")
    public User select(@RequestParam("username") String username){
        return userService.findByUserName(username);
    }

    @ApiOperation("根据用户ID查询对应的角色")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int")
    @GetMapping("/getRoleById")
    public List<Integer> getRoleById(@RequestParam("id") int id){
        return userService.getRoleByUserId(id);
    }

    @ApiOperation("根据角色ID查询对应的权限")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "int")
    @GetMapping("/getPermisonById")
    public  List<Integer> getPermisonsById(@RequestParam("id") int id){
        return userService.getPermisonsByUserId(id);
    }

    //@ApiOperation("插入用户")
    //@ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "int")
    @PostMapping("/addUser")
    public int addUser(@RequestParam("username") String username,@RequestParam("password") String password){
        User user=new User();
        String newpwd = new SimpleHash("MD5", password).toHex();

        user.setUsername(username);
        user.setPwd(newpwd);
        user.setRoleid(1);

        return userService.insertUser(user);
    }

}
