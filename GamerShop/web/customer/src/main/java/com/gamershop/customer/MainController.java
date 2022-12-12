package com.gamershop.customer;

import com.gamershop.customer.category.service.CategoryService;
import com.gamershop.shared.dto.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private final CategoryService categoryService;

    public MainController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String viewHomePage(Model model) {
        List<CategoryDTO> listCategories = categoryService.listNoChildrenCategories();
        model.addAttribute("listCategories", listCategories);
        return "index";
    }
}
