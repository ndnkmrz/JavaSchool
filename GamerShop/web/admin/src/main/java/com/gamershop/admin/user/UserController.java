package com.gamershop.admin.user;


import com.gamershop.shared.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService service;
    public UserController(UserService _service){
        this.service = _service;
    }

    @GetMapping("/users")
    public String listAll(Model model){
        Iterable<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("pageTitle", "List of users");
        return "users";
    }

    @GetMapping("/users/new")
    public String createUser(Model model){
        model.addAttribute("pageTitle", "Create new user");
        return "newuser";
    }


}
