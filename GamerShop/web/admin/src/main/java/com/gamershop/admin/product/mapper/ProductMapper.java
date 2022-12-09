package com.gamershop.admin.product.mapper;

import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.entity.ProductEntity;
import com.gamershop.shared.entity.ProductImageEntity;
import com.gamershop.shared.entity.ProductParameterEntity;
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
        String productCategory = productEntity.getProductCategory().getCategoryName();
        List<ProductImageEntity> productImages = productEntity.getProductImages();
        List<ProductParameterEntity> productParameters = productEntity.getProductParameters();
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
                productDTO.isEnabled(),
                productDTO.getProductImages(),
                productDTO.getProductParameters());
    }
}
