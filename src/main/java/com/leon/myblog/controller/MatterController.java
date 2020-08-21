package com.leon.myblog.controller;

import com.leon.myblog.service.Matterservice;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：leon
 * @date ：Created in 2020-08-21 17:48
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController
@RequestMapping("/admin")
public class MatterController {

    @Autowired
    private Matterservice matterservice;


    @GetMapping("/getAllMatters")
    public Result<List> getAllMatters(){
        if(matterservice.getAllMatters()!=null)
        {
            return ResultUtil.success(matterservice.getAllMatters());
        }
        else {
            return ResultUtil.fail("查询失败");
        }

    }
}
