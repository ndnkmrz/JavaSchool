package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.PaymentStatusDTO;
import com.gamershop.shared.entity.PaymentStatusEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentStatusMapper {
    private final OrderMapper orderMapper;

    public PaymentStatusMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public PaymentStatusDTO toDTO(PaymentStatusEntity paymentStatusEntity){
        Integer paymentStatusId = paymentStatusEntity.getPaymentStatusId();;
        String paymentStatusName = paymentStatusEntity.getPaymentStatusName();
        List<OrderDTO> paymentStatusOrders = paymentStatusEntity.getPaymentStatusOrders().stream().map(orderMapper::toDTO).toList();
        return new PaymentStatusDTO(paymentStatusId,
                paymentStatusName,
                paymentStatusOrders);

    }

    public PaymentStatusEntity toPaymentStatus(PaymentStatusDTO paymentStatusDTO){
        return new PaymentStatusEntity(paymentStatusDTO.getPaymentStatusId(),
                paymentStatusDTO.getPaymentStatusName());
    }


}
