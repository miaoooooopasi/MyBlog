package com.leon.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leon.myblog.enity.Article;
import com.leon.myblog.enity.Category;
import com.leon.myblog.enity.Timeaxi;
import com.leon.myblog.service.ArticleService;
import com.leon.myblog.service.CategoryService;
import com.leon.myblog.service.TimelineService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/front/")
public class FrontController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TimelineService timelineService;




    @ApiOperation("根据博文ID获取博文对应的详细数据")
    @ApiImplicitParam(name = "id", value = "博文ID", required = true, dataType = "Integer")
    @GetMapping("/detail")
    public Map<String, Object> detail(@RequestParam("id") Integer id)
    {
        Map<String, Object> resultMap = new HashMap<>();
        Article article=articleService.getArticleById(id);

        String content = article.getContent();

        PegDownProcessor peg=new PegDownProcessor();
        String new_content=peg.markdownToHtml(content);
        article.setContent(new_content);

        resultMap.put("article",article);
        int categoryid=articleService.getCategoryidByArticleid(id);
        Category category=categoryService.selectByID(categoryid);

        resultMap.put("category",category);

        return resultMap;
    }


    @ApiOperation("获取分类数据")
    @GetMapping("/category")
    public Map<String,Object> category(){

        Map<String, Object> resultMap = new HashMap<>();
        List<Category> categories=categoryService.getall();
        resultMap.put("category",categories);
        return resultMap;

    }


    @ApiOperation("获取博文数据")
    @GetMapping(value={"/articleList"})
    public Map<String, Object> home(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "4") Integer pageSize){

        Map<String,Object> resultMap = new HashMap<>();
        //获取前五的文章
        List<Article> top5Article=articleService.getTop5Article();
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles=articleService.getAllArticle();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Article>(articles, pageSize);
        //分页详细信息
        resultMap.put("pageInfo",pageInfo);
        //当前页
        resultMap.put("pageNum",pageInfo.getPageNum());
        //是否是第一页
        resultMap.put("isFirstPage", pageInfo.isIsFirstPage());
        //是否是最后一页
        resultMap.put("isLastPage", pageInfo.isIsLastPage());
        resultMap.put("top5Article",top5Article);
        resultMap.put("content","content");
        return resultMap;

    }

    @ApiOperation("根据分类ID获取对应的分类博文数据")
    @ApiImplicitParam(name = "categoryid", value = "分类ID", required = true, dataType = "int")
    @GetMapping("/categoryList")
    public Map<String, Object> getCategoryDetail(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "3") Integer pageSize,
                                          @RequestParam("categoryid") int categoryid){

        Map<String, Object> resultMap = new HashMap<>();
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles=articleService.getAllArticleByCategoryid(categoryid);
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Article>(articles, pageSize);
        resultMap.put("pageInfo", pageInfo);
        return resultMap;
    }

    @CrossOrigin(origins = {"127.0.0.1:8080"})
    @ApiOperation("获取时间轴内容")
    //@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/timeline")
    public Map<String, Object> getTimeline(){

        Map<String,Object> resultMap = new HashMap<>();
        List<Timeaxi> timeaxis =timelineService.getAllTimeline();
        resultMap.put("timeaxis", timeaxis);

        return resultMap;
    }

}
