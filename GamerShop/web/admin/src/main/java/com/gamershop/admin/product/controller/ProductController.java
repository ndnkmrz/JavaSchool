package com.gamershop.admin.product.controller;

import com.gamershop.admin.product.interfaces.IProductService;
import com.gamershop.shared.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
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

}
