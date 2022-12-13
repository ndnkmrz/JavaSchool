package com.gamershop.customer.product.controllers;

import com.gamershop.customer.category.interfaces.ICategoryService;
import com.gamershop.customer.product.interfaces.IProductService;
import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    private final ICategoryService categoryService;
    private final IProductService productService;


    public ProductController(ICategoryService categoryService, IProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/category/{category_id}")
    public String viewCategory(@PathVariable("category_id") Integer id, Model model){
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        List<CategoryDTO> parentCategories = categoryService.getCategoryParentsById(id);
        Page<ProductDTO> categoryProducts = productService.listByCategory(1, categoryDTO.getCategoryId());
        List<ProductDTO> productList = categoryProducts.getContent();
        model.addAttribute("parentCategories", parentCategories);
        model.addAttribute("pageTitle", categoryDTO.getCategoryName());
        model.addAttribute("productList", productList);
        return "products_by_category";
    }




}
