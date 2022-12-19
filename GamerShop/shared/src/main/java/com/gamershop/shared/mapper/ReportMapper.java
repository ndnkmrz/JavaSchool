package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.ReportDTO;
import com.gamershop.shared.entity.OrderProductEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReportMapper {
    public ReportDTO toDTO(OrderProductEntity orderProduct){
        String productName = orderProduct.getOrderProductProduct().getProductName();
        Date orderDate = orderProduct.getOrderProductOrder().getOrderDate();
        Double totalSum = orderProduct.getOrderProductQuantity() * orderProduct.getOrderProductProduct().getProductPrice();
        String customerEmail = orderProduct.getOrderProductOrder().getOrderCustomer().getUser().getUserEmail();
        return new ReportDTO(productName, orderDate, totalSum, customerEmail);
    }
}
