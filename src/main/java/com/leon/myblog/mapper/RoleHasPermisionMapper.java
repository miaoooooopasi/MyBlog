package com.leon.myblog.mapper;

import com.leon.myblog.enity.RoleHasPermisionKey;

public interface RoleHasPermisionMapper {
    int deleteByPrimaryKey(RoleHasPermisionKey key);

    int insert(RoleHasPermisionKey record);

    int insertSelective(RoleHasPermisionKey record);
}