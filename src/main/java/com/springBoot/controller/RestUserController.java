package com.springBoot.controller;

import com.springBoot.model.Role;
import com.springBoot.model.User;
import com.springBoot.service.RoleService;
import com.springBoot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private final UserService userService;
    private final RoleService roleService;

    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> showAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> create(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin")
    public ResponseEntity<User> update(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

