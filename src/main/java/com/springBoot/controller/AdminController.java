package com.springBoot.controller;

import com.springBoot.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.springBoot.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String mainPage(Model model) {
        List<User> userList = service.getAllUsers();
        model.addAttribute("users", userList);
        return "mainPage";
    }

    @GetMapping("/userPage/{id}")
    public String userPage(@PathVariable("id") Long id,Model model) {
        model.addAttribute("user", service.getUser(id));
        return "userPage";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        service.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editUser", service.getUser(id));
        return "editUser";
    }

    @PatchMapping("/saveEdit/{id}")
    public String updateUser(@ModelAttribute("editUser") User editUser,
                             @PathVariable("id") Long id,
                             Authentication authentication) {
        long authenticationUserId = service.getUserByUsername(authentication.getName()).getId();
        service.edit(editUser, id);
        if (id == authenticationUserId) {
            return "redirect:/login?logout";
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             Authentication authentication) {
        long authenticationUserId = service.getUserByUsername(authentication.getName()).getId();
        service.delete(id);
        if (id == authenticationUserId) {
            return "redirect:/login?logout";
        }
        return "redirect:/admin";
    }
}
