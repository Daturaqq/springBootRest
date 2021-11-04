package com.springBoot.controller;


import com.springBoot.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.springBoot.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String userPage(Model model, Authentication authentication) {
        model.addAttribute("user", service.getUserByUsername(authentication.getName()));
        return "userPageForUser";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editUser", service.getUser(id));
        return "editUserForUser";
    }

    @PatchMapping("/saveEdit/{id}")
    public String updateUser(@ModelAttribute("editUser") User editUser,
                             @PathVariable("id") Long id) {
        service.edit(editUser, id);
        return "redirect:/login?logout";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/login?logout";
    }
}
