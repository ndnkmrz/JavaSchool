package com.gamershop.admin.user.controller;

import com.gamershop.admin.security.GamershopUserDetails;
import com.gamershop.admin.user.interfaces.IRoleService;
import com.gamershop.admin.user.interfaces.IUserService;
import com.gamershop.shared.dto.UserDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AccountController {
    private final IUserService service;
    public AccountController(IUserService service){
        this.service = service;
    }

    @GetMapping("/account")
    public String viewDetails(@AuthenticationPrincipal GamershopUserDetails loggedUser, Model model){
        String email = loggedUser.getUsername();
        UserDTO user = service.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("cardTitle", "Your account details");
        model.addAttribute("pageTitle", "Your account details");
        return "account_form";
    }

    @PostMapping("/account/update")
    public String saveUser(UserDTO user, Model model, RedirectAttributes redirectAttributes){
        service.saveUser(user);
        UserDTO savedUser = service.getUserByEmail(user.getUserEmail());
        model.addAttribute("user", savedUser);
        redirectAttributes.addFlashAttribute("message", "Your account has been updated successfully");
        return "redirect:/account";
    }
}
