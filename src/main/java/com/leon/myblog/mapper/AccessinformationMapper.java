package com.leon.myblog.mapper;

import com.leon.myblog.enity.Accessinformation;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AccessinformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Accessinformation record);

    int insertSelective(Accessinformation record);

    Accessinformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Accessinformation record);

    int updateByPrimaryKey(Accessinformation record);

    @Select("select * from accessinformation")
    List<Accessinformation> getAll();


    @Select("select count(*) as total from accessinformation")
    int getcurrentAllAcessTotal();


    @Select("SELECT addressSimple as 省份, count(addressSimple) as 访问数量 FROM myblog.accessinformation group by 省份")
    List<Map<String,String>> getProvinceAccessTotal();
}
