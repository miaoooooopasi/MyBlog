package com.leon.myblog.service;

import com.leon.myblog.enity.Matter;
import com.leon.myblog.mapper.MatterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：leon
 * @date ：Created in 2020-08-21 16:14
 * @description：level 1：待处理  2：已处理 3：作废； degree 1：普通，2：重要 3：紧急；
 * @modified By：leon
 * @version: $version$
 */

@Service
public class Matterservice {

    @Autowired
    private MatterMapper matterMapper;

    public List<Matter> getAllMatters(){
        return matterMapper.getAllMatters();
    }
}
