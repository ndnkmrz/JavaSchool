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
    @Lob
    @Column(name = "productImageLink", columnDefinition = "BLOB")
    private byte[] productImageLink;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private ProductEntity productEntity;

    private String productImageName;

    public ProductImageEntity(byte[] productImageLink, ProductEntity productEntity, String productImageName){
        this.productImageLink = productImageLink;
        this.productEntity = productEntity;
        this.productImageName = productImageName;
    }

    public ProductImageEntity(Integer productImageId, byte[] productImageLink) {
        this.productImageId = productImageId;
        this.productImageLink = productImageLink;
    }

    public ProductImageEntity() {

    }
}
