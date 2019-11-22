package com.leon.myblog.mapper;

import com.leon.myblog.enity.RoleHasUserKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleHasUserMapper {
    int deleteByPrimaryKey(RoleHasUserKey key);

    int insert(RoleHasUserKey record);

    int insertSelective(RoleHasUserKey record);

    @Select("select * from role_has_user where user_id=#{id}")
    List<Integer> getRoleById(Integer id);
}