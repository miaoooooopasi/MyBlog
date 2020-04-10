package com.leon.myblog.mapper;

import com.leon.myblog.enity.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    @Select("select * from tag")
    List<Tag> getAllTags();

    @Select("select * from tag as t,article_has_tag as a where t.id=a.tagid and a.articleid=#{id}")
    List<Tag> getTagsById();
}
