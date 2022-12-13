package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.ProductImageDTO;
import com.gamershop.shared.entity.ProductImageEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductImageMapper {
    public ProductImageDTO toDTO(ProductImageEntity productImageEntity){
        Integer productImageId = productImageEntity.getProductImageId();
        String productImageName = productImageEntity.getProductImageName();
        return new ProductImageDTO(productImageId, productImageName);
    }

//    public ProductImageEntity toImage(ProductImageDTO productImageDTO){
//        return new ProductImageEntity(productImageDTO.getProductImageId(), productImageDTO.getProductImageName());
//    }
}
