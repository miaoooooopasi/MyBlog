package com.leon.myblog.mapper;

import com.leon.myblog.enity.Timeaxi;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TimeaxiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Timeaxi record);

    int insertSelective(Timeaxi record);

    Timeaxi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Timeaxi record);

    int updateByPrimaryKey(Timeaxi record);

    @Select("select * from timeaxi")
    List<Timeaxi> getAllTimeline();
}