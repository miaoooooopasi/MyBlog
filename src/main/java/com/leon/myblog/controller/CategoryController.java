package com.leon.myblog.controller;

import com.leon.myblog.enity.Category;
import com.leon.myblog.service.CategoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

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
     * description:  根据categoryid删除对应的分类数据
     * version: 1.0
     * date: 2019-11-08
     * author: leon
     * @return 1：成功  0：失败
     */
    @ApiOperation("删除某一条分类信息")
    @ApiImplicitParam(name = "id", value = "删除ID", required = true, dataType = "Integer")
    @DeleteMapping("/delete")
    public int DeleteCategoryById(@RequestParam("id") Integer id ){
        return categoryService.deleteCategory(id);
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
    @PostMapping("update")
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
}

