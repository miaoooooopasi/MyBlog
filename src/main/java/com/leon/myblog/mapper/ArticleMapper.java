package com.leon.myblog.mapper;

import com.leon.myblog.enity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    //int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    @Select("SELECT * FROM article order by id desc")
    @Results(value = {
            @Result(property = "articleimage", column = "imageid",
                    one = @One(select = "com.leon.myblog.mapper.ArticleimageMapper.selectByPrimaryKey")
            ),
            @Result(property = "category", column = "categoryid",
                    one = @One(select = "com.leon.myblog.mapper.CategoryMapper.getCategoryByCategoryid"))
    })
    List<Article> getAllArticle();

    @Select("SELECT * FROM article where categoryid=#{categoryid}")
    @Results({
            @Result(property = "articleimage",column = "imageid",
                    one=@One(select = "com.leon.myblog.mapper.ArticleimageMapper.selectByPrimaryKey")
            ),
            @Result(property = "category", column = "categoryid",
                    one = @One(select = "com.leon.myblog.mapper.CategoryMapper.getCategoryByCategoryid"))
    })
    List<Article> getAllArticleByCategoryid(Integer categoryid);

    @Select("select * from article order by clicknums limit 5")
    List<Article> getTop5Article();

    @Select("select categoryid from article where id=#{id}")
    int getCategoryidByArticleid(int id);

    @Select("select * from article where id=#{id}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "tags" ,column = "id",
                    many = @Many(select = "com.leon.myblog.mapper.TagMapper.getTagsById"))
    })
    Article getArticleById(Integer id);

    @Select("select * from article where content like #{keyword} or title like #{keyword};")
    List<Article> getSearchResults(String keyword);

    @Update("update article set clicknums=clicknums+1 where id =#{id}")
    void upAticleClicknum(Integer id);
}
