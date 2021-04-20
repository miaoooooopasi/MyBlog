package com.leon.myblog.mapper;

import com.leon.myblog.enity.Categoryimage;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryimageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Categoryimage record);

    int insertSelective(Categoryimage record);

    Categoryimage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Categoryimage record);

    int updateByPrimaryKey(Categoryimage record);

    @Select("select * from categoryimage")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(property = "category", column = "id",
                    one = @One(select = "com.leon.myblog.mapper.CategoryMapper.getCategorynameByCategoryImgId"))
    })
    List<Categoryimage> getAllCategoryImages();
}
