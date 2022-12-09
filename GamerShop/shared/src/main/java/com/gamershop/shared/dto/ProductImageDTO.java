package com.gamershop.shared.dto;

import com.gamershop.shared.entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageDTO {
    private Integer productImageId;
    private String productImageLink;
    private ProductEntity productEntity;
}
