package com.gamershop.admin.product.mapper;

import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.entity.ProductEntity;
import com.gamershop.shared.entity.ProductImagesEntity;
import com.gamershop.shared.entity.ProductParametersEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMapper {

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
        boolean enabled = productEntity.isEnabled();
        List<ProductImagesEntity> productImages = productEntity.getProductImages();
        List<ProductParametersEntity> productParameters = productEntity.getProductParameters();
        return new ProductDTO(productId,
                productName,
                productPrice,
                productWeight,
                productHeight,
                productWidth,
                productLength,
                productQuantity,
                productDescription,
                enabled,
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
                productDTO.isEnabled(),
                productDTO.getProductImages(),
                productDTO.getProductParameters());
    }
}
