package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.DeliveryMethodTDO;
import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.entity.DeliveryMethodEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryMethodMapper {

    private OrderMapper orderMapper;

    public DeliveryMethodMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public DeliveryMethodTDO toDTO(DeliveryMethodEntity deliveryMethodEntity){
        Integer deliveryMethodId = deliveryMethodEntity.getDeliveryMethodId();
        String deliveryMethodName = deliveryMethodEntity.getDeliveryMethodName();
        boolean enable = deliveryMethodEntity.isEnable();
        List<OrderDTO> deliveryMethodOrders = deliveryMethodEntity.getDeliveryMethodOrders().stream().map(orderMapper::toDTO).toList();
        return new DeliveryMethodTDO(deliveryMethodId,
                deliveryMethodName,
                enable,
                deliveryMethodOrders);
    }

    public DeliveryMethodEntity toDeliveryMethod(DeliveryMethodTDO deliveryMethodTDO){
        return new DeliveryMethodEntity(deliveryMethodTDO.getDeliveryMethodId(),
                deliveryMethodTDO.getDeliveryMethodName(),
                deliveryMethodTDO.isEnable());

    }
}
