package com.leon.myblog.controller;

import com.leon.myblog.enity.Article;
import com.leon.myblog.enity.User;
import com.leon.myblog.service.ArticleService;
import com.leon.myblog.service.ArticleimageService;
import com.leon.myblog.service.CategoryService;
import com.leon.myblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @ author ：leon
 * @ date ：Created in 2019-11-15 9:04
 * @ description：${description}
 * @ modified By：
 * @ version: $version$
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleimageService articleimageService;

    @GetMapping("/home")
    public ModelAndView home(){
        // 获取当前登录用户名
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();

        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        mv.addObject("user",user);
        mv.setViewName("/admin/home.html");
        return mv;
    }

    @GetMapping("/addArticle")
    public ModelAndView addArticle(){
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        mv.addObject("user",user);
        mv.addObject("category",categoryService.getall());
        mv.addObject("img",articleimageService.getAllImages());
        mv.setViewName("/admin/addArticle.html");
        return mv;
    }

    @GetMapping("/listArticle")
    public Map<String, Object> getAllArticle(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("rows",articleService.getAllArticle());
        resultMap.put("total",articleService.getAllArticle().size());
        resultMap.put("totalNotFiltered",resultMap.size());

        return resultMap;
    }

    @GetMapping("/articleManager")
    public ModelAndView getAllArticles(){
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        mv.addObject("user",user);
        //mv.addObject("articleList",articleService.getAllArticle());
        mv.setViewName("/admin/manageArticle.html");
        System.out.println("1111111");
        return mv;
    }

    @GetMapping("/editeArticle/{id}")
    public ModelAndView editeArticle(@PathVariable int id){
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        mv.addObject("user",user);
        mv.addObject("articleDetail",articleService.getArticleById(id));
        mv.addObject("category",categoryService.getall());
        mv.addObject("img",articleimageService.getAllImages());
        mv.setViewName("/admin/editeArticle.html");
        return mv;
    }

    @PostMapping("/updateArticle")
    public int updateArticle(@RequestParam("title") String title,@RequestParam("content") String content,
                             @RequestParam("pushdate") String pushdate, @RequestParam("image") String image,
                             @RequestParam("category") String category,@RequestParam("id") int id){

        Article article=new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreatetime(pushdate);
        article.setId(id);
        System.out.println(category+image);
        int categoryId = categoryService.getCategoryIdByCategoryname(category);
        article.setCategoryid(categoryId);
        int imageId=articleimageService.getImageIdByImagename(image);
        article.setImageid(imageId);
        return articleService.updateArticle(article);
    }

    @PostMapping("/insertArticle")
    public int insertArticle(@RequestParam("title") String title,@RequestParam("content") String content,
                             @RequestParam("pushdate") String pushdate, @RequestParam("image") String image,
                             @RequestParam("category") String category){

        Article article=new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setClicknums(0);
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        article.setUserid(user.getId());
        article.setImageid(articleimageService.getImageIdByImagename(image));
        article.setCategoryid(categoryService.getCategoryIdByCategoryname(category));
        int ret= articleService.insertArticle(article);
        if(ret==1){
            return 1;
        }
        else {
            return 0;
        }
    }

}
