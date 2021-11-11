package com.springBoot.controller;

import com.springBoot.model.User;
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

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        model.addAttribute("userPrincipal", userService.getUserByUsername(principal.getName()));
        return "showUser";
    }

    @GetMapping("/admin")
    public String adminPage(Principal principal, Model model, @ModelAttribute("newUser") User user) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "adminPage";
    }

    @PostMapping("/admin/save")
    public String save(@ModelAttribute("newUser") User user,
                       @RequestParam(value = "roles") String[] roles) {
        userService.saveOrUpdateWithRoles(user, roles);
        return "redirect:/admin";
    }

    @PutMapping("/admin/update")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles") String[] roles,
                         Principal principal) {
        boolean sameUser = user.getUsername().equals(principal.getName());
        userService.saveOrUpdateWithRoles(user, roles);
        if (sameUser) {
            return "redirect:/login?logout";
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") long id, Principal principal) {
        boolean sameUser = userService.getUserById(id).getUsername().equals(principal.getName());
        userService.delete(id);
        if (sameUser) {
            return "redirect:/login?logout";
        }
        return "redirect:/admin";
    }
}