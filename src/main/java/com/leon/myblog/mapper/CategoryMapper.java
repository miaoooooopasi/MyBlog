package com.leon.myblog.mapper;

import com.leon.myblog.enity.Category;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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
    @Results({
            @Result(property = "categoryimage",column = "categoryimageid",
                    one=@One(select = "com.leon.myblog.mapper.CategoryimageMapper.selectByPrimaryKey")
            )
    })
    List<Category> getallcategory();

    @Select("select id from category where categoryname=#{category}")
    int getCategoryIdByCategoryname(String category);

    @Select("select categoryname from category where categoryid=#{categoryid}")
    String getCategorynameByCategoryid(int categoryid);

    @Select("select * from category where id=#{categoryid}")
    Category getCategoryByCategoryid(Integer categoryid);

    @Select("select categoryname from category where id=#{categoryid}")
    String getCategorynameById(Integer categoryid);

}
