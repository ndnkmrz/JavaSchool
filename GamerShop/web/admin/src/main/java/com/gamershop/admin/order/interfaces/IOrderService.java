package com.gamershop.admin.order.interfaces;

import com.gamershop.shared.dto.OrderDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    Page<OrderDTO> listByPage(int pageNum, Integer orderId);
    void saveOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Integer id);
    List<String> getOrderStatus();
}
