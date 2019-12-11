package com.leon.myblog.service;

import com.leon.myblog.enity.Article;
import com.leon.myblog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：leon
 * @date ：Created in 2019-12-11 14:38
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Service
public class TestService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    // private ArticleMapper articleMapper;

    public List<Article> test(){
        return articleMapper.getAllArticle();
    }
}
