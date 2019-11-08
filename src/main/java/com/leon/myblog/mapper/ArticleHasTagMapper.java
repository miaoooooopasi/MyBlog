package com.leon.myblog.mapper;

import com.leon.myblog.enity.ArticleHasTagKey;

public interface ArticleHasTagMapper {
    int deleteByPrimaryKey(ArticleHasTagKey key);

    int insert(ArticleHasTagKey record);

    int insertSelective(ArticleHasTagKey record);
}