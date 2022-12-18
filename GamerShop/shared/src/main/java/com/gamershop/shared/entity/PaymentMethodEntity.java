package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PaymentMethods")
@Getter
@Setter
@NoArgsConstructor
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentMethodId;

    private String paymentMethodName;

    private boolean enable;

    @OneToMany(mappedBy = "orderPaymentMethod")
    private List<OrderEntity> paymentMethodOrders;

    public PaymentMethodEntity(Integer paymentMethodId, String paymentMethodName, boolean enable) {
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodName = paymentMethodName;
        this.enable = enable;
    }


    public PaymentMethodEntity(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
}
