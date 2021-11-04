package com.springBoot.service;

import com.springBoot.dao.UserDao;
import com.springBoot.model.Role;
import com.springBoot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserDao DAO;

    public UserServiceImp(UserDao DAO) {
        this.DAO = DAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return DAO.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return DAO.getUser(id);
    }

    @Override
    @Transactional
    public void add(User user) {
        DAO.add(user);
    }

    @Override
    @Transactional
    public void edit(User editUser, long id) {
        User user = DAO.getUser(id);
        user.setFirstname(editUser.getFirstname());
        user.setLastname(editUser.getLastname());
        user.setEmail(editUser.getEmail());
        user.setPassword(editUser.getPassword());
        if (!editUser.getRoles().isEmpty()) {
            user.setRoles(editUser.getRoles());
        }
        DAO.edit(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        DAO.delete(id);
    }

    @Override
    @Transactional
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return DAO.getUserByUsername(email);
    }

    @Override
    @Transactional
    public Role getRole(String roleName) {
        return DAO.getRole(roleName);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return loadUserByUsername(username);
    }

    @Override
    @Transactional
    public void createAdmin() {
        DAO.createAdmin();
    }

}
