package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="ProductParameters")
public class ProductParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productParameterId;

    @Column(length = 300, nullable = false)
    private String productParameterName;

    @Column(length = 500, nullable = false)
    private String productParameterValue;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private ProductEntity productEntity;

    public ProductParameterEntity(Integer productParameterId, String productParameterName, String productParameterValue, ProductEntity productEntity) {
        this.productParameterId = productParameterId;
        this.productParameterName = productParameterName;
        this.productParameterValue = productParameterValue;
        this.productEntity = productEntity;
    }

    public ProductParameterEntity() {
    }

    public ProductParameterEntity(String productParameterName, String productParameterValue, ProductEntity productEntity) {
        this.productParameterName = productParameterName;
        this.productParameterValue = productParameterValue;
        this.productEntity = productEntity;
    }
}
