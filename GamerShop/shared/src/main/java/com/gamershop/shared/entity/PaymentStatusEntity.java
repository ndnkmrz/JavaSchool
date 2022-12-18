package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PaymentStatus")
@Getter
@Setter
@NoArgsConstructor
public class PaymentStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentStatusId;

    private String paymentStatusName;

    @OneToMany(mappedBy = "orderPaymentStatus")
    private List<OrderEntity> paymentStatusOrders;

    public PaymentStatusEntity(Integer paymentStatusId, String paymentStatusName) {
        this.paymentStatusId = paymentStatusId;
        this.paymentStatusName = paymentStatusName;
    }

    public PaymentStatusEntity(String paymentStatusName) {
        this.paymentStatusName = paymentStatusName;
    }
}
