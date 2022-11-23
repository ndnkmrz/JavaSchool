package com.gamershop.admin.user.controller;

import com.gamershop.admin.user.interfaces.IUserService;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.web.bind.annotation.RestController
public class UserRestController {
    private final IUserService userService;

    public UserRestController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(String email){
        return userService.isEmailUnique(email) ? "OK" : "Duplicated";

    }
}
