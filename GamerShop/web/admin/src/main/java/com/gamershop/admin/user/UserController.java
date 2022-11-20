package com.gamershop.admin.user;


import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService service;
    private final Mapper mapper;
    public UserController(UserService service, Mapper mapper){

        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/users")
    public String listAll(Model model){
        List<UserDTO> listUsers = service.listUsers().stream().map(mapper::toDTO).collect(Collectors.toList());
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("pageTitle", "List of users");
        return "users";
    }

    @GetMapping("/users/new")
    public String createUser(Model model){
        UserDTO user = new UserDTO();
        Iterable<RoleEntity> listRoles = service.listRoles();
        model.addAttribute("pageTitle", "Create new user");
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "newuser";
    }

    @PostMapping("/users/save")
    public String saveUser(UserDTO user, RedirectAttributes redirectAttributes){
        UserEntity userEntity = mapper.toUser(user);
        user.getRoles().stream().map(role -> service.getOrCreateRole(role)).forEach(userEntity::addRole);
        service.saveUser(userEntity);
        redirectAttributes.addFlashAttribute("message", "The user has been saved succesfully");
        return "redirect:/users";
    }


}
