package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="OrderProduct")
@Getter
@Setter
@NoArgsConstructor
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderProductId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private OrderEntity orderProductOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productid")
    private ProductEntity orderProductProduct;

    private Integer orderProductQuantity;

    public OrderProductEntity(Integer orderProductId, Integer orderProductQuantity) {
        this.orderProductId = orderProductId;
        this.orderProductQuantity = orderProductQuantity;
    }
}
