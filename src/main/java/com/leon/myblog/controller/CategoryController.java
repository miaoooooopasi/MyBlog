package com.leon.myblog.controller;

import com.leon.myblog.enity.Category;
import com.leon.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/getAll")
    public List<Category> GetAllCategory(){
        //System.out.println(categoryService.getall().get(1));
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

    @GetMapping("get")
    public Category select(@RequestParam("id") Integer id){
        return categoryService.selectByID(id);
    }
}

