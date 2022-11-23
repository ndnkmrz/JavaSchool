package com.gamershop.admin.user.controller;
import com.gamershop.admin.user.interfaces.IRoleService;
import com.gamershop.admin.user.interfaces.IUserService;
import com.gamershop.admin.user.service.RoleService;
import com.gamershop.admin.user.service.UserService;
import com.gamershop.shared.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class UserController {

    private final IUserService userService;
    private final IRoleService roleService;
    public UserController(IUserService userService, IRoleService roleService){

        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String listAll(Model model){
        List<UserDTO> listUsers = userService.listUsers();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("pageTitle", "List of users");
        return "users";
    }

    @GetMapping("/users/new")
    public String createUser(Model model){
        UserDTO user = new UserDTO();
        List<String> listRoles = roleService.listRoles();
        model.addAttribute("pageTitle", "Create new user");
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "newuser";
    }

    @PostMapping("/users/save")
    public String saveUser(UserDTO user, RedirectAttributes redirectAttributes){

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved succesfully");
        return "redirect:/users";
    }


}
