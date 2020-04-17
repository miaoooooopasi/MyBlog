package com.leon.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leon.myblog.enity.Article;
import com.leon.myblog.service.ArticleService;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：leon
 * @date ：Created in 2020-04-09 12:33
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController
@RequestMapping("/front")
public class SearchController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/SearchResult")
    public Result<Article> getSearchResults(@RequestParam("keyword") String keyword,
                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "3") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //System.out.println(articleService.getSearchResults("%"+keyword+"%"));
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        String likestring="%"+keyword+"%";
        PageInfo<Article> pageInfo = new PageInfo<>(articleService.getSearchResults(likestring), 3);
        if (articleService.getSearchResults(keyword)!=null){
            return ResultUtil.success(pageInfo);
        }
        else
            return ResultUtil.error("没有查询的内容");
    }
}
