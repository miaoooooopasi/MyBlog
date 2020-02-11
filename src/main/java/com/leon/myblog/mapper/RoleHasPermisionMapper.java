package com.leon.myblog.mapper;

import com.leon.myblog.enity.RoleHasPermisionKey;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleHasPermisionMapper {
    int deleteByPrimaryKey(RoleHasPermisionKey key);

    int insert(RoleHasPermisionKey record);

    int insertSelective(RoleHasPermisionKey record);

    @Select("select * from role_has_permision where roleid=#{id}")
    List<Integer> getPermisonsByRoleId(int id);
}
