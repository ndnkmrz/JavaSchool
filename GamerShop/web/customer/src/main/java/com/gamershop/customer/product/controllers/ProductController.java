package com.gamershop.customer.product.controllers;

import com.gamershop.customer.category.interfaces.ICategoryService;
import com.gamershop.customer.product.interfaces.IProductService;
import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @GetMapping("/product/{product_id}")
    public String viewProduct(@PathVariable("product_id") Integer id, Model model){
        ProductDTO productDTO = productService.getProductById(id);
        List<CategoryDTO> parentCategories = categoryService.getCategoryParentsById(productDTO.getProductCategoryId());
        model.addAttribute("product", productDTO);
        model.addAttribute("parentCategories", parentCategories);
        return "/product";
    }
    @GetMapping("/products/page/{pageNum}")
    public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
                             @Param("keyword") String keyword,
                             @Param("categoryId") Integer categoryId,
                             @Param("minprice") Integer minprice,
                             @Param("maxprice") Integer maxprice){
        Page<ProductDTO> page = productService.listByPage(pageNum, keyword, categoryId);
        List<ProductDTO> listProducts = page.getContent();
        Map<CategoryDTO, Set<CategoryDTO>> listCategories = categoryService.listWithChildrenCategories();
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", "Total products: "+ page.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "Products");
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("minprice", minprice);
        model.addAttribute("maxprice", maxprice);
        return "products";
    }





}
