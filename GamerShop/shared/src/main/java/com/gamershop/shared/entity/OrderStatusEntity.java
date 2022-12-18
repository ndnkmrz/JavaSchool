package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="OrderStatus")
@Getter
@Setter
@NoArgsConstructor
public class OrderStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderStatusId;

    private String orderStatusName;

    @OneToMany(mappedBy = "orderOrderStatus")
    private List<OrderEntity> orderStatusOrders;

    public OrderStatusEntity(Integer orderStatusId, String orderStatusName) {
        this.orderStatusId = orderStatusId;
        this.orderStatusName = orderStatusName;
    }

    public OrderStatusEntity(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }
}
