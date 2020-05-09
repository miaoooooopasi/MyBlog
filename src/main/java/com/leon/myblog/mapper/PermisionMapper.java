package com.leon.myblog.mapper;

import com.leon.myblog.enity.Permision;

public interface PermisionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permision record);

    int insertSelective(Permision record);


    Permision selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permision record);

    int updateByPrimaryKey(Permision record);
}
