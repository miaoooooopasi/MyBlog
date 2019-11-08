package com.leon.myblog.service;

import com.leon.myblog.enity.Category;
import com.leon.myblog.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public int addCategory(Category category){
        return categoryMapper.insert(category);
    }

    public int deleteCategory(Integer id){
        return categoryMapper.deleteByPrimaryKey(id);

    }

    public List<Category> getall(){
        return categoryMapper.getallcategory();
    }
}
