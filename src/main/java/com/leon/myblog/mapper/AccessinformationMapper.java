package com.leon.myblog.mapper;

import com.leon.myblog.enity.Accessinformation;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccessinformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Accessinformation record);

    int insertSelective(Accessinformation record);

    Accessinformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Accessinformation record);

    int updateByPrimaryKey(Accessinformation record);

    @Select("select * from accessinformation")
    List<Accessinformation> getAll();
}
