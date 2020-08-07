package com.leon.myblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leon.myblog.enity.Article;
import com.leon.myblog.enity.Articleimage;
import com.leon.myblog.enity.User;
import com.leon.myblog.service.*;
import com.leon.myblog.utils.QiniuUtil.QiniuUploadFileServiceImpl;
import com.leon.myblog.utils.date.DateUtils1;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private AccessinformationService accessinformationService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String fileDomain = "http://img.bonjours.cn/";


    /*
     * description:  此接口用户查询article表的所有数据，后期数据多的时候必须做优化
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
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


    @GetMapping("/listImageManager")
    @RequiresRoles("admin")
    public Map<String, Object> getAllArticleImage(){
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("rows",articleimageService.getAllImages());
        resultMap.put("total",articleimageService.getAllImages().size());
        resultMap.put("totalNotFiltered",resultMap.size());

        return resultMap;
    }


    /*
     * description:  此接口根据前端提供的articleid进行条件查询
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：对应articleid的详细信息
     */
    @GetMapping("/editeArticle/{id}")
    @RequiresRoles("admin")
    public Result<Map> editeArticle(@PathVariable int id){
        Map<String, Object> map = new HashMap<>();
        map.put("articleDetail",articleService.getArticleById(id));
        if(map.size()>0){
            return ResultUtil.success(map);
        }
        else
            return ResultUtil.fail("查询失败");
    }

    /*
     * description:  此接口根据前端提供的articleid和修改的数据，进行指定articleid的更新
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
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

    /*
     * description:  此接口根据前端提供的数据，新增artilce
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    @PostMapping("/insertArticle")
    @Transactional
    public Result insertArticle(@RequestParam("title") String title,@RequestParam("content") String content,
                             @RequestParam("categoryname") String categoryname,@RequestParam("summary") String summary,
                             @RequestParam("createtime") String createtime,@RequestParam("articleImage") String articleImage){

        //PegDownProcessor peg=new PegDownProcessor();
        //String new_content=peg.markdownToHtml(content);
        //System.out.println("time:"+ DateUtils1.dealDateFormat(createtime));

        Article article=new Article();
        article.setTitle(title);
        article.setSummary(summary);
        article.setContent(content);
        article.setCreatetime(DateUtils1.dealDateFormat(createtime));
        //article.setModifytime(modifytime);
        article.setImageid(articleimageService.getImageIdByImagename(articleImage));
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



    /*
     * description:  此接口用于向七牛云上传图片并返回封装在map中对应url；此接口未做上传七牛云失败校验
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：上传七牛云成功的url
     */
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

    /*
     * description:  此接口用于上传articleimage到七牛云保存，当成功上传时，将url存入articleimage表
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    @PostMapping("/uploadArticleImg")
    public Result uploadArticleImg(@RequestParam("file") MultipartFile file,@RequestParam("title") String title){
        Map<String,String> map = new HashMap<>();
        Articleimage articleimage=new Articleimage();
        articleimage.setImgname(title);
        //System.out.println("123");
        Response response = null;
        try {
            response = qiniuUploadFileService.uploadFile(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        DefaultPutRet putRet = null;
        try {
            putRet = mapper.readValue(response.bodyString(), DefaultPutRet.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        String url = fileDomain+putRet.hash;
        articleimage.setUrl(url);
        if(articleimageService.insertArticleImage(articleimage)==1) {
            map.put("url", url);
            //map.put("name","123");
            return ResultUtil.success(map);
        }
        else
            return ResultUtil.fail("上传失败");
    }

    /*
     * description:  此接口根据前端传递的articleimageid删除对应的数据库信息；存在问题，未删除七牛云保存的图片文件
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    @PostMapping("/delArticleImg")
    public Result delArticleImg(@RequestParam("id") int id){
        if(articleimageService.delArticleImaById(id)==1){
            return ResultUtil.success("删除成功");
        }
        else
            return ResultUtil.fail("删除失败");

    }

    /****
     *
     @PostMapping("/uploadArticleImg")
     public Result uploadArticleImg(@RequestParam("file") MultipartFile file,@RequestParam("title") String title){
     Map<String,String> map = new HashMap<>();
     Articleimage articleimage=new Articleimage();
     articleimage.setImgname(title);
     try {
     Response response = qiniuUploadFileService.uploadFile(file.getInputStream());
     DefaultPutRet putRet = mapper.readValue(response.bodyString(), DefaultPutRet.class);
     String url = fileDomain+putRet.hash;
     articleimage.setUrl(url);
     if(articleimageService.insertArticleImage(articleimage)==1){
     map.put("url",url);
     map.put("name",title);
     }
     else {
     ResultUtil.fail("新增失败");
     }
     } catch (IOException e) {
     e.printStackTrace();
     }
     return ResultUtil.success(map);

     }
     ***/


    @PostMapping("/delArticleById")
    @Transactional
    public Result insertArticle(@RequestParam("id") int id){

        if(articleService.delArticleById(id)==1){
            //logger.info("{}删除博文:{}",user.getUsername(),article.toString());
            return ResultUtil.success();
        }
        else {
            return ResultUtil.fail("删除博文失败");
        }
    }


    /*
     * description:  此接口会计算所有的博文对应的访问数量然后累加出结果
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    @GetMapping("/getcurrentAllAcessTotalForHome")
    public Result getcurrentAllAcessTotal(){
        return ResultUtil.success(accessinformationService.getcurrentAllAcessTotal());
    }


    /*
     * description:  获取当前所有博文数量数据；后期做分页查询优化
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */

    @GetMapping("/getCurrentAllArticleTotal")
    public Result getCurrentAllArticleTotal(){
        return ResultUtil.success(articleService.getCurrentAllArticleTotal());
    }

    /*
     * description:  获取当年度博文的数据，根据月份
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    // 获取当年度博文的数据，根据月份
    @GetMapping("/getCurrentYearArticlesByMonth")
    public Result getCurrentYearArticlesByMonth(){
        return ResultUtil.success(articleService.getCurrentYearArticlesByMonth());
    }

    /*
     * description:  获取各个省份的历史访问数量
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    //获取各个省份的历史访问数量
    @GetMapping("/getProvinceAccessTotal")
    public Result getProvinceAccessTotal(){
        return ResultUtil.success(accessinformationService.getProvinceAccessTotal());
    }


    /*
     * description: 获取所有的博文封面数量
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    @GetMapping("/getAllArticleImages")
    public Result getAllImages(){
        return ResultUtil.success(articleimageService.getAllImages());
    }

    /*
     * description: 根据博文ID获取博文对应的详细数据
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    @ApiOperation("根据博文ID获取博文对应的详细数据")
    @ApiImplicitParam(name = "id", value = "博文ID", required = true, dataType = "int")
    @GetMapping("/getArticleById")
    public Result<Article> detail(@RequestParam("id") Integer id)
    {
        Article article=articleService.getArticleById(id);
        if (article!=null)
        {
            logger.info("前端根据ID获取博文信息内容:{}.",article.toString());
            return ResultUtil.success(article);
        }
        else
            return ResultUtil.fail("查询失败");
    }

}
