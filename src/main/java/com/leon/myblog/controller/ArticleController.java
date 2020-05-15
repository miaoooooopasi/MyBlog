package com.leon.myblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leon.myblog.enity.Article;
import com.leon.myblog.enity.User;
import com.leon.myblog.service.*;
import com.leon.myblog.utils.QiniuUtil.QiniuUploadFileServiceImpl;
import com.leon.myblog.utils.date.DateUtils1;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
public class ArticleController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private ArticleimageService articleimageService;

    @Autowired
    private QiniuUploadFileServiceImpl qiniuUploadFileService;

    @Autowired
    private ObjectMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String fileDomain = "http://img.bonjours.cn/";

    @GetMapping("/addArticle")
    public ModelAndView addArticle(){
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        mv.addObject("user",user);
        mv.addObject("category",categoryService.getall());
        mv.addObject("img",articleimageService.getAllImages());
        mv.setViewName("admin/addArticle.html");
        return mv;
    }


    @GetMapping("/listArticle")
    @Transactional(rollbackFor = Exception.class)
    public Result<Article> getAllArticle(){
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("rows",articleService.getAllArticle());
        resultMap.put("total",articleService.getAllArticle().size());
        resultMap.put("totalNotFiltered",resultMap.size());

        if (articleService.getAllArticle()!=null&&resultMap.size()!=0){
            return ResultUtil.success(resultMap);
        }
        else
            return ResultUtil.fail("查询失败");
    }

    @GetMapping("/articleImageManager")
    @RequiresRoles("admin")
    public ModelAndView getAllArticleImages(){
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        ModelAndView mv = new ModelAndView();
        User user=userService.findByUserName(currentUser);
        mv.addObject("user",user);

        mv.setViewName("admin/manageArticleImage.html");

        return mv;
    }

    @GetMapping("/listImageManager")
    @RequiresRoles("admin")
    public Map<String, Object> getAllArticleImage(){
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("rows",articleimageService.getAllImages());
        resultMap.put("total",articleimageService.getAllImages().size());
        resultMap.put("totalNotFiltered",resultMap.size());

        return resultMap;
    }


    @GetMapping("/editeArticle/{id}")
    @RequiresRoles("admin")
    public Result<Map> editeArticle(@PathVariable int id){
        //String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        //ModelAndView mv = new ModelAndView();
        //User user=userService.findByUserName(currentUser);
        Map<String, Object> map = new HashMap<>();
        map.put("articleDetail",articleService.getArticleById(id));
        if(map.size()>0){
            return ResultUtil.success(map);
        }
        else
            return ResultUtil.fail("查询失败");
    }

    @PostMapping("/updateArticle")
    @RequiresRoles("admin")
    public Result<Map> updateArticle(@RequestParam("title") String title,@RequestParam("content") String content,
                             @RequestParam("summary") String summary,
                             @RequestParam("pushdate") String pushdate, @RequestParam("image") String image,
                             @RequestParam("category") String category,@RequestParam("id") int id){


        Article article;
        article = new Article();
        article.setTitle(title);
        article.setSummary(summary);
        article.setContent(content);
        article.setCreatetime(pushdate);
        article.setId(id);
        //System.out.println(category+image);
        int categoryId = categoryService.getCategoryIdByCategoryname(category);
        article.setCategoryid(categoryId);
        int imageId=articleimageService.getImageIdByImagename(image);
        article.setImageid(imageId);
        //articleService.updateArticle(article);
        if (1==articleService.updateArticle(article))
        {
            logger.info("更新博文成功");
            return ResultUtil.success();
         }
         else
             return ResultUtil.fail("更新失败");


    }

    @PostMapping("/insertArticle")
    @Transactional
    public Result insertArticle(@RequestParam("title") String title,@RequestParam("content") String content,
                             @RequestParam("categoryname") String categoryname,@RequestParam("summary") String summary,
                             @RequestParam("createtime") String createtime){

        //PegDownProcessor peg=new PegDownProcessor();
        //String new_content=peg.markdownToHtml(content);
        //System.out.println("time:"+ DateUtils1.dealDateFormat(createtime));

        Article article=new Article();
        article.setTitle(title);
        article.setSummary(summary);
        article.setContent(content);
        article.setCreatetime(DateUtils1.dealDateFormat(createtime));
        //article.setModifytime(modifytime);
        article.setImageid(4);
        article.setClicknums(0);
        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
        User user=userService.findByUserName(currentUser);
        article.setUserid(user.getId());
        //article.setImageid(articleimageService.getImageIdByImagename(imagename));
        article.setCategoryid(categoryService.getCategoryIdByCategoryname(categoryname));
        System.out.println(categoryService.getCategoryIdByCategoryname(categoryname));
        if(articleService.insertArticle(article)==1){
            logger.info("{}新建博文:{}",user.getUsername(),article.toString());
            return ResultUtil.success();
        }
        else {
            return ResultUtil.fail("保存博文失败");
        }
    }



    @PostMapping("/uploadImg")
    //@RequiresRoles("admin")
    public Result uploadImg(@RequestParam("file") MultipartFile file){
        Map<String,String> map = new HashMap<>();
        try {
            Response response = qiniuUploadFileService.uploadFile(file.getInputStream());
            DefaultPutRet putRet = mapper.readValue(response.bodyString(), DefaultPutRet.class);
            String url = fileDomain+putRet.hash;
            map.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.success(map);

    }


    @PostMapping("/delArticleById")
    @Transactional
    public Result insertArticle(@RequestParam("id") int id){

        if(articleService.delArticleById(id)==1){
            //logger.info("{}新建博文:{}",user.getUsername(),article.toString());
            return ResultUtil.success();
        }
        else {
            return ResultUtil.fail("保存博文失败");
        }
    }

}
