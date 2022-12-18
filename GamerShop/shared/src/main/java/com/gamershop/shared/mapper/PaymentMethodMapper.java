package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.DeliveryMethodTDO;
import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.PaymentMethodTDO;
import com.gamershop.shared.entity.DeliveryMethodEntity;
import com.gamershop.shared.entity.PaymentMethodEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodMapper {
    private OrderMapper orderMapper;

    public PaymentMethodMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public PaymentMethodTDO toDTO(PaymentMethodEntity paymentMethodEntity){
        Integer paymentMethodId = paymentMethodEntity.getPaymentMethodId();
        String paymentMethodName = paymentMethodEntity.getPaymentMethodName();
        boolean enable = paymentMethodEntity.isEnable();
        List<OrderDTO> paymentMethodOrders = paymentMethodEntity.getPaymentMethodOrders().stream().map(orderMapper::toDTO).toList();
        return new PaymentMethodTDO(paymentMethodId,
                paymentMethodName,
                enable,
                paymentMethodOrders);
    }

    public PaymentMethodEntity toPaymentMethod(PaymentMethodTDO paymentMethodTDO){
        return new PaymentMethodEntity(paymentMethodTDO.getPaymentMethodId(),
                paymentMethodTDO.getPaymentMethodName(),
                paymentMethodTDO.isEnable());
    }
}
