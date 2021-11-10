package com.springBoot.dao;

import com.springBoot.model.User;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String email);

    List<User> getAllUsers();

    void save(User user);

    void saveOrUpdate(User user);

    User getUserById(long id);

    void delete(long id);

    User loadUserByUsername(String email);
}

