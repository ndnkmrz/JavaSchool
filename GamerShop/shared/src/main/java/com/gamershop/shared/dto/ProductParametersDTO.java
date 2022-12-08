package com.gamershop.shared.dto;

import com.gamershop.shared.entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductParametersDTO {
    private Integer productParameterId;
    private String productParameterName;
    private String productParameterValue;
    private ProductEntity productEntity;
}
