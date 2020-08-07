package com.leon.myblog.mapper;

import com.leon.myblog.enity.Weburls;

public interface WeburlsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weburls record);

    int insertSelective(Weburls record);

    Weburls selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weburls record);

    int updateByPrimaryKey(Weburls record);
}