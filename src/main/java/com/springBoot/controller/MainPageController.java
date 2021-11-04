package com.springBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.springBoot.service.UserService;

@Controller
public class MainPageController {

    public MainPageController(UserService service) {
        service.createAdmin();
    }

    @GetMapping("/login")
    public String loginInPage() {
        return "loginInPage";
    }
}
