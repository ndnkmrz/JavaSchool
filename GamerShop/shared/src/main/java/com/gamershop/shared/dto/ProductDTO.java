package com.gamershop.shared.dto;

import com.gamershop.shared.entity.ProductImagesEntity;
import com.gamershop.shared.entity.ProductParametersEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Double productWeight;
    private Double productHeight;
    private Double productWidth;
    private Double productLength;
    private Integer productQuantity;
    private String productDescription;
    private boolean enabled;
    private List<ProductImagesEntity> productImages;
    private List<ProductParametersEntity> productParameters;

    public ProductDTO(Integer productId, String productName, Double productPrice, Double productWeight, Double productHeight, Double productWidth, Double productLength, Integer productQuantity, String productDescription, boolean enabled) {
    }
}
