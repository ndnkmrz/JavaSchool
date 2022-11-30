package com.gamershop.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model){
        model.addAttribute("pageTitle", "Login page");
        return "login";
    }
}
