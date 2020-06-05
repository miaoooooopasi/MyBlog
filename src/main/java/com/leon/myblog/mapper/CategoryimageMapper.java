package com.leon.myblog.mapper;

import com.leon.myblog.enity.Categoryimage;
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
    List<Categoryimage> getAllCategoryImages();
}
