package com.springBoot.service;

import com.springBoot.dao.UserDao;
import com.springBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final UserDao DAO;

    @Autowired
    public UserServiceImpl(RoleService roleService, UserDao DAO) {
        this.roleService = roleService;
        this.DAO = DAO;
    }

    @Override
    public User getUserByUsername(String username) {
        return DAO.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return DAO.getAllUsers();
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        DAO.save(roleService.addRoleForUser(user));
    }

    @Override
    public void saveOrUpdate(User user) {
        User userDB = getUserById(user.getId());
        if (user.getPassword().isEmpty()) {
            user.setPassword(userDB.getPassword());
        } else if (!bCryptPasswordEncoder.matches(userDB.getPassword(), bCryptPasswordEncoder.encode(user.getPassword()))) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        DAO.saveOrUpdate(roleService.addRoleForUser(user));
    }

    @Override
    public User getUserById(long id) {
        return DAO.getUserById(id);
    }

    @Override
    public void delete(long id) {
        DAO.delete(id);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return DAO.loadUserByUsername(email);
    }
}

