package com.gamershop.customer.customer.controller;

import com.gamershop.customer.customer.interfaces.ICustomerService;
import com.gamershop.customer.security.GamershopUserDetails;
import com.gamershop.shared.dto.AddressDTO;
import com.gamershop.shared.dto.CustomerDTO;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.UserEntity;
import com.gamershop.shared.mapper.CustomerMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        UserDTO userDTO = new UserDTO();
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("user", userDTO);
        model.addAttribute("cardTitle", "Register");
        return "register";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(UserDTO userDTO, RedirectAttributes redirectAttributes){
        customerService.saveUser(userDTO);
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

    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal GamershopUserDetails loggedUser, Model model){
        if (loggedUser == null){
            return "login";
        }
        String email = loggedUser.getUsername();
        UserDTO user = customerService.getUserByEmail(email);
        CustomerDTO customer = customerService.getCustomerByUserId(user.getId());
        model.addAttribute("customer", customer);
        model.addAttribute("pageTitle", "Profile " + email);
        model.addAttribute("cardTitle", "Profile " + email);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String editProfile(CustomerDTO customerDTO, Model model, RedirectAttributes redirectAttributes){
        customerService.saveCustomer(customerDTO);
        redirectAttributes.addFlashAttribute("message", "Your profile has been updated successfully");
        return "redirect:/profile";
    }

    @GetMapping("/account")
    public String viewAccount(@AuthenticationPrincipal GamershopUserDetails loggedUser, Model model){
        if (loggedUser == null){
            return "login";
        }
        String email = loggedUser.getUsername();
        UserDTO user = customerService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Account " + email);
        model.addAttribute("cardTitle", "Account " + email);
        return "account";
    }

    @PostMapping("/account/update")
    public String editAccount(UserDTO userDTO, Model model, RedirectAttributes redirectAttributes){
        customerService.saveUser(userDTO);
        redirectAttributes.addFlashAttribute("message", "Your password has been updated successfully");
        return "redirect:/";
    }

    @GetMapping("/address")
    public String showAddress(@AuthenticationPrincipal GamershopUserDetails loggedUser, Model model){
        if (loggedUser == null){
            return "login";
        }
        String email = loggedUser.getUsername();
        UserDTO user = customerService.getUserByEmail(email);
        CustomerDTO customer = customerService.getCustomerByUserId(user.getId());
        model.addAttribute("addresses", customer.getCustomerAddresses());
        model.addAttribute("customerId", customer.getCustomerId());
        return "address";

    }
    @GetMapping("/address/new/{id}")
    public String createAddress(Model model, @PathVariable(name="id") Integer id){
        AddressDTO address = new AddressDTO();
        address.setAddressCustomer(id);
        model.addAttribute("pageTitle", "Add new address");
        model.addAttribute("address", address);
        model.addAttribute("cardTitle", "Add new address");
        return "address_form";
    }

    @PostMapping("/address/save")
    public String saveAddress(AddressDTO addressDTO, Model model, RedirectAttributes redirectAttributes){
        customerService.saveAddress(addressDTO);
        redirectAttributes.addFlashAttribute("message", "Your address has been saved successfully");
        return "redirect:/address";
    }

    @GetMapping("/address/edit/{id}")
    public String updateAddress(@PathVariable(name="id") Integer id, Model model){
        AddressDTO address = customerService.getAddressById(id);
        model.addAttribute("address", address);
        model.addAttribute("pageTitle", "Edit address");
        model.addAttribute("cardTitle", "Edit address");
        return "address_form";
    }

}
