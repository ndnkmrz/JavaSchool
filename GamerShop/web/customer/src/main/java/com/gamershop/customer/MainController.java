package com.gamershop.customer;

import com.gamershop.customer.category.interfaces.ICategoryService;
import com.gamershop.customer.category.service.CategoryService;
import com.gamershop.shared.dto.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {
    private final ICategoryService categoryService;

    public MainController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String viewHomePage(Model model) {
        Map<CategoryDTO, Set<CategoryDTO>> listCategories = categoryService.listWithChildrenCategories();
        model.addAttribute("listCategories", listCategories);
        return "index";
    }

}
