package com.springBoot.service;

import com.springBoot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);

    List<User> getAllUsers();

    void save(User user);

    void saveOrUpdate(User user);

    void saveOrUpdateWithRoles(User user, String[] roles);

    User getUserById(long id);

    void delete(long id);
}
