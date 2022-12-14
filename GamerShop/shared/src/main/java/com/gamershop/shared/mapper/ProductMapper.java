package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.dto.ProductImageDTO;
import com.gamershop.shared.dto.ProductParameterDTO;
import com.gamershop.shared.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {
    private final ProductImageMapper productImageMapper;
    private final ProductParameterMapper productParameterMapper;

    public ProductMapper(ProductImageMapper productImageMapper, ProductParameterMapper productParameterMapper) {
        this.productImageMapper = productImageMapper;
        this.productParameterMapper = productParameterMapper;
    }

    public ProductDTO toDTO(ProductEntity productEntity){
        Integer productId = productEntity.getProductId();
        String productName = productEntity.getProductName();
        Double productPrice = productEntity.getProductPrice();
        Double productWeight = productEntity.getProductWeight();
        Double productHeight = productEntity.getProductHeight();
        Double productWidth = productEntity.getProductWidth();
        Double productLength = productEntity.getProductLength();
        Integer productQuantity = productEntity.getProductQuantity();
        String productDescription = productEntity.getProductDescription();
        Integer productCategoryId = productEntity.getProductCategory().getCategoryId();
        boolean enabled = productEntity.isEnabled();
        String productCategory;
        if (productEntity.getProductCategory() != null){
            productCategory = productEntity.getProductCategory().getCategoryName();
        }
        else{
            productCategory = null;
        }
        List<ProductImageDTO> productImages = productEntity.getProductImages().stream()
                .map(productImageMapper::toDTO).toList();
        List<ProductParameterDTO> productParameters = productEntity.getProductParameters().stream()
                .map(productParameterMapper::toDTO).toList();
        return new ProductDTO(productId,
                productName,
                productPrice,
                productWeight,
                productHeight,
                productWidth,
                productLength,
                productQuantity,
                productDescription,
                productCategoryId,
                enabled,
                productCategory,
                productImages,
                productParameters);
    }

    public ProductEntity toProduct(ProductDTO productDTO){
        return new ProductEntity(productDTO.getProductId(),
                productDTO.getProductName(),
                productDTO.getProductPrice(),
                productDTO.getProductWeight(),
                productDTO.getProductHeight(),
                productDTO.getProductWidth(),
                productDTO.getProductLength(),
                productDTO.getProductQuantity(),
                productDTO.getProductDescription(),
                productDTO.isEnabled());
    }
}
