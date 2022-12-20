package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.OrderProductDTO;
import com.gamershop.shared.entity.AddressEntity;
import com.gamershop.shared.entity.OrderEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String orderCustomerEmail = orderEntity.getOrderCustomer().getUser().getUserEmail();
        Integer orderAddress = orderEntity.getOrderAddress().getAddressId();
        AddressEntity address = orderEntity.getOrderAddress();
        String orderAddressText = address.getAddressCountry() + ", " + address.getAddressCity() + "\n" + address.getAddressStreet()
                + ", " + address.getAddressHouseNum() + ", " + address.getAddressApartNum() +  "\n" + address.getAddressPostCode();
        List<OrderProductDTO> orderProductsOrders = orderEntity.getOrderProductsOrders().stream().map(orderProductMapper::toDTO).toList();
        String orderOrderStatus = orderEntity.getOrderOrderStatus().getOrderStatusName();
        String orderDeliveryMethod = orderEntity.getOrderDeliveryMethod().getDeliveryMethodName();
        String orderPaymentMethod = orderEntity.getOrderPaymentMethod().getPaymentMethodName();
        String orderPaymentStatus = orderEntity.getOrderOrderStatus().getOrderStatusName();
        Double finalSum = getSum(orderEntity);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = dateFormat.format(orderEntity.getOrderDate());
        return new OrderDTO(orderId,
                orderCustomer,
                orderCustomerEmail,
                orderAddress,
                orderAddressText,
                orderProductsOrders,
                orderOrderStatus,
                orderDeliveryMethod,
                orderPaymentMethod,
                orderPaymentStatus,
                finalSum,
                orderDate);
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
