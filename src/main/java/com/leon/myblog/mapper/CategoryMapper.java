package com.leon.myblog.mapper;

import com.leon.myblog.enity.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    @Select("SELECT * FROM category")
    List<Category> getallcategory();

    @Select("select id from category where categoryname=#{category}")
    int getCategoryIdByCategoryname(String category);

    @Select("select categoryname from category where categoryid=#{categoryid}")
    String getCategorynameByCategoryid(int categoryid);

}