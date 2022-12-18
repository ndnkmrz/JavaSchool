package com.gamershop.shared.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethodTDO {
    private Integer paymentMethodId;
    private String paymentMethodName;
    private boolean enable;
    private List<OrderDTO> paymentMethodOrders;
}
