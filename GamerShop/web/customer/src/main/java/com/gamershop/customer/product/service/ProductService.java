package com.gamershop.customer.product.service;

import com.gamershop.customer.exception.ProductNotFoundException;
import com.gamershop.customer.product.interfaces.IProductService;
import com.gamershop.shared.mapper.ProductMapper;
import com.gamershop.customer.category.service.CategoryService;
import com.gamershop.customer.product.repo.ProductRepository;
import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.entity.ProductEntity;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    public static final int PRODUCTS_PER_PAGE = 12;
    public final CategoryService categoryService;

    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    public Page<ProductDTO> listByCategory(int pageNum, Integer categoryId){
        Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
        var listCategoriesId = categoryService.getCategoryChildrenById(categoryId);
        Page<ProductEntity> productEntities = productRepository
                .findAllByProductCategory_CategoryIdIn(listCategoriesId, pageable);
        List<ProductDTO> products = productEntities.stream().map(productMapper::toDTO).toList();
        return new PageImpl<>(products, pageable, productEntities.getTotalElements());
    }

    public ProductDTO getProductById(Integer productId){
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Could not find this product"));
        return productMapper.toDTO(productEntity);
    }
    public ProductEntity getProductEntityById(Integer productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Could not find this product"));
    }
    public Page<ProductDTO> listByPage(int pageNum,
                                       String keyword,
                                       Integer categoryId,
                                       Double minPrice,
                                       Double maxPrice){
        Pageable pageable = PageRequest.of(pageNum -1, PRODUCTS_PER_PAGE);
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        if (keyword != null) {
            keyword = "%" + keyword + "%";
            List<Integer> ids = categoryId == 0 ? categoryService.getAllCategoryId() : categoryService
                    .getCategoryChildrenById(categoryId);
            minPrice = minPrice == null ? 0.0 : minPrice;
            maxPrice = maxPrice == null ? Double.MAX_VALUE : maxPrice;
            productEntities = productRepository
                    .findAllByProductCategory_CategoryIdInAndProductNameLikeAndProductPriceBetween(ids,
                    keyword,
                    minPrice,
                    maxPrice,
                    pageable);
        }
        List<ProductDTO> products = productEntities.stream().map(productMapper::toDTO).toList();
        return new PageImpl<>(products, pageable, productEntities.getTotalElements());
    }

}
