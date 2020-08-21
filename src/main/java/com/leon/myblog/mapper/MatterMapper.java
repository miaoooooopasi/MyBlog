package com.leon.myblog.mapper;

import com.leon.myblog.enity.Matter;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MatterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Matter record);

    int insertSelective(Matter record);

    Matter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Matter record);

    int updateByPrimaryKey(Matter record);

    @Select("select * from matter")
    List<Matter> getAllMatters();
}
