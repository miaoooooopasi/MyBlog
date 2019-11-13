package com.leon.myblog.service;

import com.leon.myblog.enity.RoleHasUserKey;
import com.leon.myblog.enity.User;
import com.leon.myblog.mapper.RoleHasUserMapper;
import com.leon.myblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author ：leon
 * @date ：Created in 2019-11-11 14:47
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class UserService {

    @Autowired
    private UserMapper  userMapper;

    @Autowired
    private RoleHasUserMapper roleHasUserMapper;

    public User findByUserName(String username) {
        return userMapper.get(username);
    }

    public int insertUser(User user){
        return userMapper.insert(user);
    }

    public List<RoleHasUserKey> getRoleByUserId(int id) {
        return roleHasUserMapper.getRoleById(id);
    }
}
