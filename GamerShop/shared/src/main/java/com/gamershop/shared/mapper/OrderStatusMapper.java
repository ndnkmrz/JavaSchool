package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.OrderStatusDTO;
import com.gamershop.shared.entity.OrderStatusEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusMapper {
    private final OrderMapper orderMapper;

    public OrderStatusMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public OrderStatusDTO toDTO(OrderStatusEntity orderStatusEntity){
        Integer orderStatusId = orderStatusEntity.getOrderStatusId();
        String orderStatusName = orderStatusEntity.getOrderStatusName();
        List<OrderDTO> orderStatusOrders = orderStatusEntity.getOrderStatusOrders().stream().map(orderMapper::toDTO).toList();
        return new OrderStatusDTO(orderStatusId,
                orderStatusName,
                orderStatusOrders);
    }

    public OrderStatusEntity toOrderStatus(OrderStatusDTO orderStatusDTO){
        return new OrderStatusEntity(orderStatusDTO.getOrderStatusId(),
                orderStatusDTO.getOrderStatusName());
    }

}
