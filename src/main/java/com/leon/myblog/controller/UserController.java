package com.leon.myblog.controller;
/*
 *@创建人：leon
 *@创建时间： 2019/11/11
 *@描述：
 */

import com.leon.myblog.enity.User;
import com.leon.myblog.service.UserService;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUserInfoByUsername")
    public Result select(@RequestParam("username") String username){
        if (userService.findByUserName(username)!=null){
            return ResultUtil.success(userService.findByUserName(username));
        }
        else
            return ResultUtil.fail("查询失败");
    }


    @GetMapping("/getRoleByUserId")
    public Result getRoleByUserId(@RequestParam("id") int id){
        if (userService.getRoleByUserId(id).size()>0)
        {
            return ResultUtil.success(userService.getRoleByUserId(id));
        }
        else
            return ResultUtil.fail("查询失败");
    }


    @GetMapping("/getUserPermissionByUserId")
    public Result getPermisonsById(@RequestParam("id") int id){
        if (userService.getPermisonsByUserId(id).size()>0)
        {
            return ResultUtil.success(userService.getPermisonsByUserId(id));
        }
        else
            return ResultUtil.fail("查询失败");
    }


    @PostMapping("/addUser")
    public Result addUser(@RequestParam("username") String username, @RequestParam("password") String password,
                          @RequestParam(defaultValue = "") String email, @RequestParam(defaultValue = "") String phone,
                          @RequestParam(defaultValue = "") String gender, @RequestParam("roleid") Integer roleid){
        User user=new User();
        String newpwd = new SimpleHash("MD5", password).toHex();
        user.setUsername(username);
        user.setPwd(newpwd);
        user.setRoleid(roleid);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        if (userService.insertUser(user)==1)
        {
            return ResultUtil.success();
        }
        else
            return ResultUtil.fail("新增用户失败");

    }

    @PostMapping("/delUser")
    public Result delUser(@RequestParam("id") Integer id){
        if (userService.delUser(id)==1)
        {
            return ResultUtil.success();
        }
        else
            return ResultUtil.fail("删除用户失败");
    }

}
