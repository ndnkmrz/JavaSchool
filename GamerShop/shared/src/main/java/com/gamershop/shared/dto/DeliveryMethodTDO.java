package com.gamershop.shared.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryMethodTDO {
    private Integer deliveryMethodId;
    private String deliveryMethodName;
    private boolean enable;
    private List<OrderDTO> deliveryMethodOrders;
}
