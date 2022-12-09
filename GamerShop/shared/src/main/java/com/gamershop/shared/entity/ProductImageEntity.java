package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="ProductImages")
public class ProductImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productImageId;
    private String productImageLink;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private ProductEntity productEntity;

    public ProductImageEntity(Integer productImageId, String productImageLink, ProductEntity productEntity) {
        this.productImageId = productImageId;
        this.productImageLink = productImageLink;
        this.productEntity = productEntity;
    }

    public ProductImageEntity() {

    }
}
