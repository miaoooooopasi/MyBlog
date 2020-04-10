package com.leon.myblog.controller;

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
    public Result<Article> getSearchResults(@RequestParam("keyword") String keyword){
        if (articleService.getSearchResults(keyword)!=null){
            return ResultUtil.success(articleService.getSearchResults("%"+keyword+"%"));
        }
        else
            return ResultUtil.error("没有查询的内容");
    }
}
