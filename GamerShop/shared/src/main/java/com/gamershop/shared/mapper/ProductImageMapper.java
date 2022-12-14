package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.ProductImageDTO;
import com.gamershop.shared.entity.ProductImageEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ProductImageMapper {
    public ProductImageDTO toDTO(ProductImageEntity productImageEntity){
        Integer productImageId = productImageEntity.getProductImageId();
        String productImageName = productImageEntity.getProductImageName();
        String productImageLink = Base64.getEncoder().encodeToString(productImageEntity.getProductImageLink());
        return new ProductImageDTO(productImageId, productImageName, productImageLink);
    }

//    public ProductImageEntity toImage(ProductImageDTO productImageDTO){
//        return new ProductImageEntity(productImageDTO.getProductImageId(), productImageDTO.getProductImageName());
//    }
}
