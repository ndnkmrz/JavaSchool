package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.OrderProductDTO;
import com.gamershop.shared.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMapper {
    private OrderProductMapper orderProductMapper;

    public OrderMapper(OrderProductMapper orderProductMapper) {
        this.orderProductMapper = orderProductMapper;
    }

    public OrderDTO toDTO(OrderEntity orderEntity){
        Integer orderId = orderEntity.getOrderId();
        Integer orderCustomer = orderEntity.getOrderCustomer().getCustomerId();
        Integer orderAddress = orderEntity.getOrderAddress().getAddressId();
        List<OrderProductDTO> orderProductsOrders = orderEntity.getOrderProductsOrders().stream().map(orderProductMapper::toDTO).toList();
        return new OrderDTO(orderId,
                orderCustomer,
                orderAddress,
                orderProductsOrders);
    }

    public OrderEntity toOrder(OrderDTO orderDTO){
        return new OrderEntity(orderDTO.getOrderId());
    }
}
