package com.springBoot.service;

import com.springBoot.model.Role;
import com.springBoot.model.User;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);

    List<Role> getAllRoles();

    void saveRole(Role role);

    User addRoleForUser(User user);
}
