package com.springBoot.service;

import com.springBoot.dao.RoleDao;
import com.springBoot.model.Role;
import com.springBoot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao DAO;

    public RoleServiceImpl(RoleDao DAO) {
        this.DAO = DAO;
    }

    @Override
    public Role getRoleByName(String name) {
        return DAO.getRoleByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return DAO.getAllRoles();
    }

    @Override
    public void saveRole(Role role) {
        DAO.saveRole(role);
    }

    @Override
    public User addRoleForUser(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        Role adminRole = getRoleByName("ROLE_ADMIN");
        Role userRole = getRoleByName("ROLE_USER");
        for (String role : roles) {
            if (role.equals("ADMIN")) {
                roleSet.add(adminRole);
            } else if (role.equals("USER")) {
                roleSet.add(userRole);
            }
        }
        user.setRoles(roleSet);
        return user;
    }
}

