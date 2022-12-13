package com.gamershop.admin.product.mapper;

import com.gamershop.shared.dto.ProductParameterDTO;
import com.gamershop.shared.entity.ProductParameterEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductParameterMapper {
    public ProductParameterDTO toDTO(ProductParameterEntity productParameterEntity){
        Integer productParameterId = productParameterEntity.getProductParameterId();
        String productParameterName = productParameterEntity.getProductParameterName();
        String productParameterValue = productParameterEntity.getProductParameterValue();
        return new ProductParameterDTO(productParameterId,productParameterName, productParameterValue);
    }
}
