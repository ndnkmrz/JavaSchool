package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.OrderProductDTO;
import com.gamershop.shared.entity.OrderEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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
        String orderOrderStatus = orderEntity.getOrderOrderStatus().getOrderStatusName();
        String orderDeliveryMethod = orderEntity.getOrderDeliveryMethod().getDeliveryMethodName();
        String orderPaymentMethod = orderEntity.getOrderPaymentMethod().getPaymentMethodName();
        Double finalSum = getSum(orderEntity);
        return new OrderDTO(orderId,
                orderCustomer,
                orderAddress,
                orderProductsOrders,
                orderOrderStatus,
                orderDeliveryMethod,
                orderPaymentMethod,
                finalSum);
    }
    private Double getSum(OrderEntity order){
        var products = order.getOrderProductsOrders();
        Double sum = 0.0;
        for(var product : products){
            sum += product.getOrderProductProduct().getProductPrice() * product.getOrderProductQuantity();
        }
        return sum;
    }
    public OrderEntity toOrder(OrderDTO orderDTO){

        return new OrderEntity(orderDTO.getOrderId());
    }

}
