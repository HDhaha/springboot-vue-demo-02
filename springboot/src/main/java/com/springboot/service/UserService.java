package com.springboot.service;

import com.springboot.domain.User;

import java.util.List;

/**
 * @author 申浩东
 */
public interface UserService {

    User getUser(Integer id);

    List<User> getUsers();

    boolean saveUser(User user);

    List<User> selectUser(String search);

    boolean updateUser(User usse);

    boolean deldeteUser(Integer id);
}
