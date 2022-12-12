package com.gamershop.shared.dto;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageDTO {
    private Integer productImageId;
    private String productImageName;

}
