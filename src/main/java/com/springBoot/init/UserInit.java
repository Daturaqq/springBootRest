package com.springBoot.init;

import org.springframework.stereotype.Component;
import com.springBoot.model.Role;
import com.springBoot.model.User;
import com.springBoot.service.RoleService;
import com.springBoot.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class UserInit {

    private final UserService userService;
    private final RoleService roleService;

    public UserInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        User user = new User();
        user.setName("Admin");
        user.setSurname("admin");
        user.setAge(99);
        user.setEmail("admin@mail.com");
        user.setPassword("admin");
        user.setRoles(Set.of(roleService.getRoleByName("ROLE_ADMIN"), roleService.getRoleByName("ROLE_USER")));
        userService.save(user);

        User user2 = new User();
        user2.setName("Vasya");
        user2.setSurname("Petrov");
        user2.setAge(45);
        user2.setEmail("Vasya@mail.com");
        user2.setPassword("123");
        user2.setRoles(Set.of(roleService.getRoleByName("ROLE_USER")));
        userService.save(user2);
    }
}
