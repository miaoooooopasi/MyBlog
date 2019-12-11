package com.leon.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leon.myblog.enity.Article;
import com.leon.myblog.enity.Category;
import com.leon.myblog.service.ArticleService;
import com.leon.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author ：leon
 * @date ：Created in 2019-12-06 15:13
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RestController
@RequestMapping("/front")
public class FrontController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/homes")
    public ModelAndView home(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("/front/home.html");
        return mv;
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("id") Integer id)
    {
        Article article=articleService.getArticleById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("article",article);

        mv.setViewName("/front/detail.html");
        return mv;
    }

    @GetMapping("/category")
    public ModelAndView category(){

        ModelAndView mv=new ModelAndView();
        List<Category> categories=categoryService.getall();
        mv.addObject("category",categories);
        mv.setViewName("/front/category.html");
        return mv;

    }

    @GetMapping("/home")
    public ModelAndView home(@RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "2") Integer pageSize){

        //int categoryid=categoryService.getCategoryIdByCategoryname(category);

        //获取前五的文章
        List<Article> top5Article=articleService.getTop5Article();
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        //List<Article> articles=articleService.getAllArticleByCategoryid(categoryid);
        List<Article> articles=articleService.getAllArticle();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Article>(articles, pageSize);
        ModelAndView mv= new ModelAndView();
        //分页详细信息
        mv.addObject("pageInfo",pageInfo);
        //System.out.println(pageInfo);
        //当前页
        mv.addObject("pageNum", pageInfo.getPageNum());
        //是否是第一页
        mv.addObject("isFirstPage", pageInfo.isIsFirstPage());
        //是否是最后一页
        mv.addObject("isLastPage", pageInfo.isIsLastPage());
        mv.addObject("top5Article",top5Article);
        mv.setViewName("/front/home.html");

        return mv;

    }

}
