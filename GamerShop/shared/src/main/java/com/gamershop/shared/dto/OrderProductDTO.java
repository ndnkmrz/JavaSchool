package com.gamershop.shared.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDTO {
    private Integer orderProductId;
    private Integer orderProductOrder;
    private ProductDTO orderProductProduct;
    Integer orderProductQuantity;
}
