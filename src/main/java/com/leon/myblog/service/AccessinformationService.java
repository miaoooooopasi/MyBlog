package com.leon.myblog.service;

import com.leon.myblog.enity.Accessinformation;
import com.leon.myblog.mapper.AccessinformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ：leon
 * @date ：Created in 2020-04-21 13:37
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class AccessinformationService {

    /**
     *
     */
    @Autowired
    AccessinformationMapper accessinformationMapper;

    public int insertOneAccesinformation(Accessinformation accessinformation){
        return accessinformationMapper.insertSelective(accessinformation);
    }

    public List<Accessinformation> getAll(){
        return accessinformationMapper.getAll();
    }


    public int getcurrentAllAcessTotal(){
        return accessinformationMapper.getcurrentAllAcessTotal();
    }

    public List<Map<String,String>> getProvinceAccessTotal(){
        return accessinformationMapper.getProvinceAccessTotal();
    }
}
