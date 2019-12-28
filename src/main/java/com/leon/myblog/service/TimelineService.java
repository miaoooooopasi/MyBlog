package com.leon.myblog.service;

import com.leon.myblog.enity.Timeaxi;
import com.leon.myblog.mapper.TimeaxiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：leon
 * @date ：Created in 2019-12-27 12:59
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class TimelineService {

    /**
     *
     */
    @Autowired
    private TimeaxiMapper timeaxiMapper;

    public List<Timeaxi> getAllTimeline(){


        return timeaxiMapper.getAllTimeline();
    }
}
