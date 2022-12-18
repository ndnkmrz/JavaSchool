package com.gamershop.shared.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusDTO {
    private Integer orderStatusId;
    private String orderStatusName;
    private List<OrderDTO> orderStatusOrders;

}
