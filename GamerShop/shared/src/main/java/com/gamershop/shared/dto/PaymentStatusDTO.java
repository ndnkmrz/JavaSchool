package com.gamershop.shared.dto;

import com.gamershop.shared.entity.OrderEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentStatusDTO {
    private Integer paymentStatusId;
    private String paymentStatusName;
    private List<OrderDTO> paymentStatusOrders;

}
