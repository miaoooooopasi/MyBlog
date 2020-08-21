package com.leon.myblog.mapper;

import com.leon.myblog.enity.Matter;

public interface MatterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Matter record);

    int insertSelective(Matter record);

    Matter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Matter record);

    int updateByPrimaryKey(Matter record);
}