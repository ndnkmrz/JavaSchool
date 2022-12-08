package com.gamershop.admin.product.service;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.admin.product.interfaces.IProductService;
import com.gamershop.admin.product.repo.ProductRepo;
import com.gamershop.admin.product.mapper.ProductMapper;
import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.entity.ProductEntity;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public static final int PRODUCTS_PER_PAGE = 10;


    public ProductService(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
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
}
