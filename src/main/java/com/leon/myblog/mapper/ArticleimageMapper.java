package com.leon.myblog.mapper;

import com.leon.myblog.enity.Articleimage;

public interface ArticleimageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Articleimage record);

    int insertSelective(Articleimage record);

    Articleimage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Articleimage record);

    int updateByPrimaryKey(Articleimage record);
}