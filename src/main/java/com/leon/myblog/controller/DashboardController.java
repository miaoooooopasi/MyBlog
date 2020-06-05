package com.leon.myblog.controller;

import com.leon.myblog.service.AccessinformationService;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：leon
 * @date ：Created in 2020-04-21 15:13
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController
@RequestMapping("/front")
public class DashboardController {

    @Autowired
    AccessinformationService accessinformationService;

    @GetMapping("/getAllAccessinformation")
    public Result getAllAccessinformation(){
        return ResultUtil.success(accessinformationService.getAll());
    }



}
