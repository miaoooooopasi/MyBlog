package com.leon.myblog.service;

import com.leon.myblog.enity.Tag;
import com.leon.myblog.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：leon
 * @date ：Created in 2020-03-02 13:48
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class TagsService {

    @Autowired
    TagMapper tagMapper;

    public List<Tag> getAllTags(){
        return tagMapper.getAllTags();
    }

    public int insertTag(Tag tag){
        return tagMapper.insert(tag);
    }

    public int delTag(Integer id){
        return tagMapper.deleteByPrimaryKey(id);
    }
}
