package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.OrderProductDTO;
import com.gamershop.shared.dto.ProductDTO;
import com.gamershop.shared.entity.OrderProductEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderProductMapper {
    private final ProductMapper productMapper;

    public OrderProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderProductDTO toDTO(OrderProductEntity orderProductEntity){
        Integer orderProductId = orderProductEntity.getOrderProductId();
        Integer orderProductOrder = orderProductEntity.getOrderProductOrder().getOrderId();
        ProductDTO orderProductProduct = productMapper.toDTO(orderProductEntity.getOrderProductProduct());
        Integer orderProductQuantity = orderProductEntity.getOrderProductQuantity();
        return new OrderProductDTO(orderProductId,
                orderProductOrder,
                orderProductProduct,
                orderProductQuantity);

    }

    public OrderProductEntity toOrderProduct(OrderProductDTO orderProductDTO){
        return new OrderProductEntity(orderProductDTO.getOrderProductId(),
                orderProductDTO.getOrderProductQuantity());
    }
}
