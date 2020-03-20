package com.leon.myblog.mapper;

import com.leon.myblog.enity.ArticleHasTagKey;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleHasTagMapper {
    int deleteByPrimaryKey(ArticleHasTagKey key);

    int insert(ArticleHasTagKey record);

    int insertSelective(ArticleHasTagKey record);

    @Select("select * from article_has_tag where articleid=#{id}")
    List<ArticleHasTagKey> getTagsIdByArticleId(int id);
}
