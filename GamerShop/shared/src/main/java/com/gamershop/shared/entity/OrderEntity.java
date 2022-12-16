package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private CustomerEntity orderCustomer;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private AddressEntity orderAddress;

    @OneToMany(mappedBy = "orderProductOrder")
    private List<OrderProductEntity> orderProductsOrders;

    public OrderEntity(Integer orderId) {
        this.orderId = orderId;
    }
}
