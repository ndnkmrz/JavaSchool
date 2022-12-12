package com.gamershop.admin.product.mapper;

import com.gamershop.shared.dto.ProductImageDTO;
import com.gamershop.shared.entity.ProductImageEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductImageMapper {
    public ProductImageDTO toDTO(ProductImageEntity productImageEntity){
        Integer productImageId = productImageEntity.getProductImageId();
        byte[] productImageLink = productImageEntity.getProductImageLink();
        Integer productId = productImageEntity.getProductEntity().getProductId();
        return new ProductImageDTO(productImageId, productImageLink, productId);
    }

    public ProductImageEntity toImage(ProductImageDTO productImageDTO){
        return new ProductImageEntity(productImageDTO.getProductImageId(), productImageDTO.getProductImageLink());
    }
}
