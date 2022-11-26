package com.gamershop.admin.user.controller;
import com.gamershop.admin.user.interfaces.IRoleService;
import com.gamershop.admin.user.interfaces.IUserService;
import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class UserController {

    private final IUserService userService;
    private final IRoleService roleService;
    private UserRepository userRepo;
    public UserController(IUserService userService, IRoleService roleService){

        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String listFirstPage(Model model){
        return listByPage(1, model, "userName", "asc");

    }

    @GetMapping("/users/new")
    public String createUser(Model model){
        UserDTO user = new UserDTO();
        List<String> listRoles = roleService.listRoles();
        model.addAttribute("pageTitle", "Create new user");
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("cardTitle", "Create new user");

        return "user_form";
    }

    @GetMapping("/users/page/{pageNum}")
    public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir){
        Page<UserDTO> page = userService.listByPage(pageNum, sortField, sortDir);
        List<UserDTO> listUsers = page.getContent();
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("totalItems", "Total users: "+ page.getTotalElements());
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);
        return "users";
    }

    @PostMapping("/users/save")
    public String saveUser(UserDTO user, RedirectAttributes redirectAttributes){
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved succesfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable(name="id") Integer id, Model model){
        List<String> listRoles = roleService.listRoles();
        UserDTO user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Edit " + user.getUserEmail());
        model.addAttribute("cardTitle", "Edit "+ user.getUserEmail());
        model.addAttribute("listRoles", listRoles);
        return "user_form";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name="id") Integer id, RedirectAttributes redirectAttributes){
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "The user "+ id+" has been deleted succesfully");
        return "redirect:/users";
    }








}
