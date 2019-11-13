package com.leon.myblog.service;

import com.leon.myblog.enity.RoleHasPermisionKey;
import com.leon.myblog.enity.RoleHasUserKey;
import com.leon.myblog.enity.User;
import com.leon.myblog.mapper.RoleHasPermisionMapper;
import com.leon.myblog.mapper.RoleHasUserMapper;
import com.leon.myblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserService {

    @Autowired
    private UserMapper  userMapper;

    /**
     *
     */
    @Autowired
    private RoleHasUserMapper roleHasUserMapper;

    @Autowired
    private RoleHasPermisionMapper roleHasPermisionMapper;

    public User findByUserName(String username) {
        return userMapper.get(username);
    }

    public int insertUser(User user){
        return userMapper.insert(user);
    }

    public List<RoleHasUserKey> getRoleByUserId(int id) {
        return roleHasUserMapper.getRoleById(id);
    }

    public List<RoleHasPermisionKey> getPermisonsByUserId(int id) {
        return roleHasPermisionMapper.getPermisonsByRoleId(id);
    }

}
