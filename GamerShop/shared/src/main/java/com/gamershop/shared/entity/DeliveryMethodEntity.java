package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="DeliveryMethods")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deliveryMethodId;

    private String deliveryMethodName;

    private boolean enable;

    @OneToMany(mappedBy = "orderDeliveryMethod")
    private List<OrderEntity> deliveryMethodOrders;

    public DeliveryMethodEntity(Integer deliveryMethodId, String deliveryMethodName, boolean enable) {
        this.deliveryMethodId = deliveryMethodId;
        this.deliveryMethodName = deliveryMethodName;
        this.enable = enable;
    }

    public DeliveryMethodEntity(String deliveryMethodName) {
        this.deliveryMethodName = deliveryMethodName;
    }
}
