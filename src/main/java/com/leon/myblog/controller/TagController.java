package com.leon.myblog.controller;

import com.leon.myblog.enity.Tag;
import com.leon.myblog.service.TagsService;
import com.leon.myblog.utils.result.Result;
import com.leon.myblog.utils.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：leon
 * @date ：Created in 2020-04-09 10:29
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RestController
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagsService tagsService;

    @GetMapping("/getTags")
    public Result<Tag> getTags(){

        Map<String, Object> map = new HashMap<>();
        map.put("articleDetail",tagsService.getAllTags());
        return ResultUtil.success(map);
    }

    @PostMapping("/insertTag")
    public Result insertTag(@RequestParam("tagname") String tagname) {
        Tag tag = new Tag();
        tag.setTagname(tagname);
        if (tagsService.insertTag(tag)==1)
        {
            return ResultUtil.success();
        }
        else
            return ResultUtil.fail("新增tag失败");
    }

    @PostMapping("/delTag")
    public Result delTag(@RequestParam("id") Integer id){
        if (tagsService.delTag(id)==1){
            return ResultUtil.success();
        }
        else
            return ResultUtil.fail("删除失败");
    }
}
