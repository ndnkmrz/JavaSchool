package com.gamershop.shared.dto;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer orderId;
    private Integer orderCustomer;
    private Integer orderAddress;
    private List<OrderProductDTO> orderProductsOrders;
    public void addProduct(OrderProductDTO product){
        this.orderProductsOrders.add(product);
    }
}
