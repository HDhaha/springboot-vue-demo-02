package com.springboot.service.impl;

import com.springboot.domain.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 申浩东
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUser(Integer id) {
        User user = userMapper.getUser(id);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public boolean saveUser(User user) {
//        if(user.getId()){
//
//        }
        int i = userMapper.saveUser(user);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<User> selectUser(String search) {
        return userMapper.selectUser(search);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user) >0 ? true:false;
    }

    @Override
    public boolean deldeteUser(Integer id) {
        return userMapper.deleteUser(id) >0 ?true:false;
    }
}
