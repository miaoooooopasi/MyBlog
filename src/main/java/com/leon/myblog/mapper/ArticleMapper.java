package com.leon.myblog.mapper;

import com.leon.myblog.enity.Article;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    @Select("SELECT * FROM article order by createtime")
    @Results({
            @Result(property = "articleimage",column = "imageid",
                    one=@One(select = "com.leon.myblog.mapper.ArticleimageMapper.selectByPrimaryKey")
            )
    })
    List<Article> getAllArticle();

    @Select("SELECT * FROM article where categoryid=#{categoryid}")
    @Results({
            @Result(property = "articleimage",column = "imageid",
                    one=@One(select = "com.leon.myblog.mapper.ArticleimageMapper.selectByPrimaryKey")
            )
    })
    List<Article> getAllArticleByCategoryid(Integer categoryid);

    @Select("select * from article order by clicknums limit 5")
    List<Article> getTop5Article();

    @Select("select categoryid from article where id=#{id}")
    int getCategoryidByArticleid(int id);
}