package com.leon.myblog.service;

import com.leon.myblog.enity.*;
import com.leon.myblog.mapper.*;
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

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermisionMapper permisionMapper;


    public User findByUserName(String username) {
        return userMapper.get(username);
    }

    public int insertUser(User user){
        return userMapper.insert(user);
    }

    public List<Integer> getRoleByUserId(int id) {
        return roleHasUserMapper.getRoleById(id);
    }

    public Role getRoleByRileId(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Integer> getPermisonsByUserId(int id) {
        return roleHasPermisionMapper.getPermisonsByRoleId(id);
    }

    public Permision getPermisonByPermisionId(int id) {
        return permisionMapper.selectByPrimaryKey(id);
    }

    public int delUser(Integer id){
        return userMapper.deleteByPrimaryKey(id);
    }

}
