package com.springBoot.controller;

import com.springBoot.model.Role;
import com.springBoot.model.User;
import com.springBoot.service.RoleService;
import com.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/user")
    public User getPrincipal(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    @PatchMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(id);
        return "User with Id: " + id + " was deleted";
    }
}

