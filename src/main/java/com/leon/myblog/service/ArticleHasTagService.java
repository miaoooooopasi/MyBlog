package com.leon.myblog.service;

import com.leon.myblog.enity.ArticleHasTagKey;
import com.leon.myblog.mapper.ArticleHasTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：leon
 * @date ：Created in 2020-03-04 15:06
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class ArticleHasTagService {

    /**
     *  根据article id 获取所有对应的标签id
     */
    @Autowired
    ArticleHasTagMapper articleHasTagMapper;

    public List<ArticleHasTagKey> getTagsByArticleId(int id){
        return articleHasTagMapper.getTagsIdByArticleId(id);
    }
}
