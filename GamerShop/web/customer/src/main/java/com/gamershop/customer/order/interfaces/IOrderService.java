package com.gamershop.customer.order.interfaces;

import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.entity.OrderStatusEntity;
import com.gamershop.shared.entity.PaymentStatusEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IOrderService {
    void saveOrder(OrderDTO orderDTO);
    PaymentStatusEntity getOrCreatePaymentStatus(String paymentStatusName);
    OrderStatusEntity getOrCreateOrderStatus(String orderStatusName);
    List<String> listPaymentMethods();
    List<String> listDeliveryMethods();
    OrderDTO getOrderById(Integer id);
    OrderDTO reorder(Integer orderId);
}
