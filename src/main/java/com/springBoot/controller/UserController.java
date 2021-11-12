package com.springBoot.controller;

import com.springBoot.service.RoleService;
import com.springBoot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        return "userPage";
    }

    @GetMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "adminPage";
    }
}