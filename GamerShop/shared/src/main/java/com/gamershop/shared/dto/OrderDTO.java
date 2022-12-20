package com.gamershop.shared.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer orderId;
    private Integer orderCustomer;
    private String orderCustomerEmail;
    private Integer orderAddress;
    private String orderAddressText;
    private List<OrderProductDTO> orderProductsOrders;
    private String orderOrderStatus;
    private String orderDeliveryMethod;
    private String orderPaymentMethod;
    private String orderPaymentStatus;
    private Double finalSum;
    private String orderDate;
    public void addProduct(OrderProductDTO product){
        this.orderProductsOrders.add(product);
    }
}
