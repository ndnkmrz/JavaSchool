package com.gamershop.customer.customer.controller;

import com.gamershop.customer.customer.interfaces.ICustomerService;
import com.gamershop.shared.dto.CustomerDTO;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.mapper.CustomerMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    private final ICustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(ICustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/register")
    public String createCustomer(Model model){
        CustomerDTO customerDTO = new CustomerDTO();
        UserDTO userDTO = new UserDTO();
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("customer", customerDTO);
        model.addAttribute("user", userDTO);
        model.addAttribute("cardTitle", "Register");
        return "register";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(UserDTO userDTO, RedirectAttributes redirectAttributes){
        customerService.saveCustomer(userDTO);
        redirectAttributes.addFlashAttribute("message", "You have been registered successfully. Now you can enter with your login and password");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String viewLoginPage()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }

}
