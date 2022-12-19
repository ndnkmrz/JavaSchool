package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Orders")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private CustomerEntity orderCustomer;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private AddressEntity orderAddress;

    @OneToMany(mappedBy = "orderProductOrder")
    private List<OrderProductEntity> orderProductsOrders;

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "orderStatusId")
    private OrderStatusEntity orderOrderStatus;

    @ManyToOne
    @JoinColumn(name = "payment_status_id", referencedColumnName = "PaymentStatusId")
    private PaymentStatusEntity orderPaymentStatus;

    @ManyToOne
    @JoinColumn(name = "delivery_method_id", referencedColumnName = "deliveryMethodId")
    private DeliveryMethodEntity orderDeliveryMethod;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "paymentMethodId")
    private PaymentMethodEntity orderPaymentMethod;

    public OrderEntity(Integer orderId) {
        this.orderId = orderId;
    }
}
