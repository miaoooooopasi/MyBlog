package com.leon.myblog.mapper;

import com.leon.myblog.enity.Articleimage;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleimageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Articleimage record);

    int insertSelective(Articleimage record);

    Articleimage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Articleimage record);

    int updateByPrimaryKey(Articleimage record);

    @Select("select * from articleimage")
    List<Articleimage> getAllImages();

    @Select("select id from articleimage where imgname=#{image}")
    int getImageIdByImagename(String image);

    @Select("select url from articleimage where id=#{id}")
    String getUrlById(int id);
}