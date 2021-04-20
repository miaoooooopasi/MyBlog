package com.leon.myblog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leon.myblog.enity.Timeaxi;
import com.leon.myblog.service.TimelineService;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class TimlineController {

    private TimelineService timelineService;

    @GetMapping("/getAllTimeline")
    public Result<List> getAllTimeline(){

        Map<String, Object> resultMap = new HashMap<>();
        //获取前五的文章
        //List<Article> top5Article=articleService.getTop5Article();
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(1, 5);
        List<Timeaxi> a = timelineService.getAllTimeline();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Timeaxi>(a, 5);
        //分页详细信息
        //resultMap.put("pageInfo",pageInfo);
        if (pageInfo != null) {
            return ResultUtil.success(pageInfo);
        }
        else {
            return ResultUtil.fail("未查询到相关信息");
        }

    }

}
