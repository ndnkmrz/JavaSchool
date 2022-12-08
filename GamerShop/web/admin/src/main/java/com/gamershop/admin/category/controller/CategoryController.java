package com.gamershop.admin.category.controller;

import com.gamershop.admin.category.interfaces.ICategoryService;
import com.gamershop.shared.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String listFirstPage(Model model){
        model.addAttribute("pageTitle", "Categories");
        return listByPage(1, model, "categoryName", "asc", null);
    }

    @GetMapping("/categories/page/{pageNum}")
    public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword){
        Page<CategoryDTO> page = categoryService.listByPage(pageNum, sortField, sortDir, keyword);
        List<CategoryDTO> listCategories = page.getContent();
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("totalItems", "Total categories: "+ page.getTotalElements());
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "Categories");
        return "categories";
    }

    @PostMapping("/categories/save")
    public String saveCategory(CategoryDTO category, RedirectAttributes redirectAttributes){
        categoryService.saveCategory(category);
        redirectAttributes.addFlashAttribute("message", "The category has been saved successfully");
        return "redirect:/categories";
    }

    @GetMapping("/categories/new")
    public String createCategory(Model model){
        CategoryDTO category = new CategoryDTO();
        List<String> listCategories = categoryService.listCategoriesUsedInForm();
        model.addAttribute("pageTitle", "Create new category");
        model.addAttribute("category", category);
        model.addAttribute("cardTitle", "Create new category");
        model.addAttribute("listCategories", listCategories);
        return "category_form";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable(name="id") Integer id, Model model){
        List<String> listCategories = categoryService.listCategoriesUsedInForm();
        CategoryDTO category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", "Edit " + category.getCategoryName());
        model.addAttribute("cardTitle", "Edit "+ category.getCategoryName());
        model.addAttribute("listCategories", listCategories);
        return "category_form";
    }


}
