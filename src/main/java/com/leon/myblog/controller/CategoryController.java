package com.leon.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leon.myblog.enity.Category;
import com.leon.myblog.enity.Categoryimage;
import com.leon.myblog.service.ArticleService;
import com.leon.myblog.service.CategoryService;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;


    /*
     * description:  在分类表插入单条分类数据
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return 1：成功  0：失败
     */
    @PostMapping("/insert")
    public int InsertCategory(@RequestParam("categoryname") String categoryname,@RequestParam("categoryimageid") Integer categoryimageid){
        Category category=new Category();
        category.setCategoryname(categoryname);
        category.setCategoryimageid(categoryimageid);
        return categoryService.addCategory(category);

    }

    /*
     * description:  根据categoryid删除对应的分类数据，删除前会判断该分类是否已存在于article表，此接口存在隐患，当a
     * 当article表数据非常多，判断会较为缓慢，后期可做优化
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return ：成功 、失败
     */
    @ApiOperation("删除某一条分类信息")
    @ApiImplicitParam(name = "id", value = "删除ID", required = true, dataType = "Integer")
    @PostMapping("/deleteCategoryById")
    public Result DeleteCategoryById(@RequestParam("id") Integer id ){

        //System.out.println(categoryService.deleteCategory(id));
        //return categoryService.deleteCategory(id);

        if(articleService.getAllArticleByCategoryid(id)==null)
        {
            categoryService.deleteCategory(id);
            return ResultUtil.success();
        }
        else {
            try {
                categoryService.deleteCategory(id);
            }
            catch (Exception E)
            {
                System.out.println(E);
            }
            return ResultUtil.fail("该分类已被博文引用，无法删除！");
        }

    }

    /*
     * description:  查询所有的category信息
     * version: 1.0
     * date:   2019-11-08
     * author: leon
     * @return map
     */
 /*   @ApiOperation("获取所有分类信息")
    @GetMapping("/getAll")
    public PageInfo<Category> GetAllCategory(){
        //System.out.println(categoryService.getall().get(1));
        PageHelper.startPage(1, 5);

        List<Category> listKind  = categoryService.getall();//从数据库中查出所有数据
        PageInfo<Category> pageInfo=new PageInfo(listKind,5);
        //System.out.println("总共条数："+page.getTotal());
        System.out.println(listKind);

        //return categoryService.getall();
        return pageInfo;

    }*/

    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory(){
        return categoryService.getall();
    }



    @GetMapping("/getAllCategoryImage")
    /*
    public Result getAllCategoryImage(){
        return ResultUtil.success(categoryService.getAllCategoryImage());
    }
    */
    public Result<List> getAllCategoryImage(@RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "3") Integer pageSize) {

        Map<String, Object> resultMap = new HashMap<>();
        //获取前五的文章
        //List<Article> top5Article=articleService.getTop5Article();
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        List<Categoryimage> a = categoryService.getAllCategoryImage();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<Categoryimage>(a, pageSize);
        //分页详细信息
        //resultMap.put("pageInfo",pageInfo);
        if (pageInfo != null) {
            return ResultUtil.success(pageInfo);
        }
        else {
            return ResultUtil.fail("未查询到相关信息");
        }
    }
    /*
     * description: 该接口提供管理系统的home页的分类饼图
     * version: 1.0
     * date:   2020-02-11
     * author: leon
     * params:
     * @return:
     */
    @GetMapping("/getCategoryForHome")
    public Result getCategoryForHome(){

        Map<String,Object> resultMap = new HashMap();

        //System.out.println(articleService.getCategoryForHome());
        resultMap.put("rows",articleService.getCategoryForHome());
        return ResultUtil.success(resultMap);
    }


    /*
     * description: 根据分类数据的ID修改该条分类数据信息
     * version: 1.0
     * date:   2019-11-11
     * author: leon
     * params:id,categoryname

     * @return 1:成功 0：失败
     */
    @ApiOperation("更新某一条分类信息")
    @ApiImplicitParam(name = "id", value = "更新ID", required = true, dataType = "Integer")
    @PostMapping("/update")
    public int update(@RequestParam("categoryname") String categoryname,@RequestParam("id") Integer id){
        Category category=categoryService.selectByID(id);
        if(category!=null)
        {
            category.setCategoryname(categoryname);
        }

        return categoryService.updateBySelectInfo(category);
    }

    /*
     * description: 根据ID查询该ID分类数据
     * version: 1.0
     * date:   2019-11-11
     * author: leon
     * params:id

     * @return
     */
    @ApiOperation("获取某一条分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Integer")
    @GetMapping("get")
    public Category select(@RequestParam("id") Integer id){
        return categoryService.selectByID(id);
    }


    /*
     * description: 插入分类封面数据
     * version: 1.0
     * date:   2021-04-19
     * author: leon
     * params:

     * @return
     */
    //@ApiOperation("插入一条分类封面图片")
    //@ApiImplicitParam(name = "url", value = "url", required = true, dataType = "String")
    @PostMapping
    public Result<List> insertCategoryImg(@RequestParam("url") String url){
        Categoryimage categoryimage=new Categoryimage();
        categoryimage.setUrl(url);
        try {
           int ret = categoryService.insertCategoryImg(categoryimage);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return ResultUtil.success("插入成功");
    }



}

