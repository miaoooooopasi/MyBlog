package com.leon.myblog.mapper;

import com.leon.myblog.enity.Categoryimage;

public interface CategoryimageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Categoryimage record);

    int insertSelective(Categoryimage record);

    Categoryimage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Categoryimage record);

    int updateByPrimaryKey(Categoryimage record);
}