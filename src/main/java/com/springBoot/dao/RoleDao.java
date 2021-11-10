package com.springBoot.dao;

import com.springBoot.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);

    List<Role> getAllRoles();

    void saveRole(Role role);
}
