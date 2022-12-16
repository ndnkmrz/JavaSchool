package com.gamershop.shared.dto;

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
    private Integer productCategoryId;
    private boolean enabled = true;
    private String productCategory;
    private List<ProductImageDTO> productImages;
    private List<ProductParameterDTO> productParameters;
    private List<OrderProductDTO> productProductsOrders;

}
