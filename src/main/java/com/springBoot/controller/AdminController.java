package com.springBoot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.springBoot.model.User;
import com.springBoot.service.RoleService;
import com.springBoot.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String mainPage(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "mainPage";
    }

    @GetMapping("/userPage/{id}")
    public String userPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userPage";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editUser", userService.getUserById(id));
        return "editUser";
    }

    @PutMapping("/saveEdit/{id}")
    public String updateUser(@ModelAttribute("editUser") User editUser,
                             @PathVariable("id") Long id,
                             Authentication authentication) {
        long authenticationUserId = userService.getUserByUsername(authentication.getName()).getId();
        userService.saveOrUpdate(editUser);
        if (id == authenticationUserId) {
            return "redirect:/login?logout";
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             Authentication authentication) {
        long authenticationUserId = userService.getUserByUsername(authentication.getName()).getId();
        userService.delete(id);
        if (id == authenticationUserId) {
            return "redirect:/login?logout";
        }
        return "redirect:/admin";
    }
}
