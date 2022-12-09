package com.gamershop.admin.product.controller;

import com.gamershop.admin.category.interfaces.ICategoryService;
import com.gamershop.admin.product.interfaces.IProductService;
import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.dto.UserDTO;
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
public class ProductController {
    private final IProductService productService;
    private final ICategoryService categoryService;

    public ProductController(IProductService productService, ICategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/products")
    public String listFirstPage(Model model){
        model.addAttribute("pageTitle", "Products");
        return listByPage(1, model, "productName", "asc", null);
    }
    @GetMapping("/products/page/{pageNum}")
    public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword){
        Page<ProductDTO> page = productService.listByPage(pageNum, sortField, sortDir, keyword);
        List<ProductDTO> listProducts = page.getContent();
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("totalItems", "Total products: "+ page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", reverseSortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "Products");
        return "products";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model){
        List<String> listCategories = categoryService.listCategoriesUsedInForm();
        ProductDTO product = new ProductDTO();
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Create new product");
        model.addAttribute("cardTitle", "Create new product");
        return "products/product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(ProductDTO product, RedirectAttributes redirectAttributes){
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("message", "The product has been saved successfully");
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable(name = "id") Integer id, Model model){
        List<String> listCategories = categoryService.listCategoriesUsedInForm();
        ProductDTO product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Edit" + product.getProductName());
        model.addAttribute("cardTitle", "Edit" + product.getProductName());
        model.addAttribute("listCategories", listCategories);
        return "products/product_form";
    }

}
