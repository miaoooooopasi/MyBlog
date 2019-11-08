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
}

