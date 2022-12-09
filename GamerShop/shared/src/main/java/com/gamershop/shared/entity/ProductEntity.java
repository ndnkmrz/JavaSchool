package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Double productWeight;
    private Double productHeight;
    private Double productWidth;
    private Double productLength;
    private Integer productQuantity;
    @Column(length = 500, nullable = false)
    private String productDescription;
    private boolean enabled = true;

    @OneToMany(mappedBy = "productEntity")
    private List<ProductImagesEntity> productImages;

    @OneToMany(mappedBy = "productEntity")
    private List<ProductParametersEntity> productParameters;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private CategoryEntity productCategory;


    public ProductEntity(Integer productId, String productName, Double productPrice, Double productWeight, Double productHeight, Double productWidth, Double productLength, Integer productQuantity, String productDescription, boolean enabled, List<ProductImagesEntity> productImages, List<ProductParametersEntity> productParameters) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeight = productWeight;
        this.productHeight = productHeight;
        this.productWidth = productWidth;
        this.productLength = productLength;
        this.productQuantity = productQuantity;
        this.productDescription = productDescription;
        this.enabled = enabled;
    }

    public ProductEntity() {
    }
}
