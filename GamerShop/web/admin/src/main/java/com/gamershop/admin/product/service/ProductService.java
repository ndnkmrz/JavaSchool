package com.gamershop.admin.product.service;

import com.gamershop.admin.category.interfaces.ICategoryService;
import com.gamershop.admin.category.service.CategoryService;
import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.admin.product.interfaces.IProductService;
import com.gamershop.admin.product.repo.ProductRepo;
import com.gamershop.admin.product.mapper.ProductMapper;
import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.entity.CategoryEntity;
import com.gamershop.shared.entity.ProductEntity;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public static final int PRODUCTS_PER_PAGE = 10;

    private final CategoryService categoryService;


    public ProductService(ProductRepo productRepo, ProductMapper productMapper, CategoryService categoryService) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductDTO> listProducts(){
        var productList = (List<ProductEntity>) productRepo.findAll();
        return productList.stream().map(productMapper::toDTO).toList();
    }

    public Page<ProductDTO> listByPage(int pageNum,
                                        String sortField,
                                        String sortDir,
                                        String keyword){
        Page<ProductEntity> productEntities;
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum -1, PRODUCTS_PER_PAGE, sort);
        if (keyword != null){
            keyword = "%" + keyword + "%";
            productEntities = productRepo.getProductEntitiesByProductNameLike(keyword, pageable);
        }
        else {
            productEntities = productRepo.findAll(pageable);
        }
        List<ProductDTO> products = productEntities.stream().map(productMapper::toDTO).toList();
        return new PageImpl<>(products, pageable, productEntities.getTotalElements());
    }

    public ProductDTO getProductById(Integer id){
        ProductEntity product = productRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Could not find any product with ID " + id));
        return productMapper.toDTO(product);
    }

    public void saveProduct(ProductDTO productDTO){
        ProductEntity product = productMapper.toProduct(productDTO);
        if (!productDTO.getProductCategory().isEmpty()){
            product.setProductCategory(categoryService.getOrCreateCategory(productDTO.getProductCategory()));
        }
        productRepo.save(product);
    }



}
