package com.gamershop.admin.order.service;

import com.gamershop.admin.exception.OrderNotFoundException;
import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.admin.order.interfaces.IOrderService;
import com.gamershop.admin.order.repo.*;
import com.gamershop.shared.dto.OrderDTO;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.*;
import com.gamershop.shared.mapper.OrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    public static final int ORDERS_PER_PAGE = 10;
    private final OrderRepository orderRepo;
    private final OrderMapper orderMapper;
    private final OrderStatusRepository orderStatusRepo;


    public OrderService(OrderRepository orderRepo, OrderMapper orderMapper,
                        OrderStatusRepository orderStatusRepo, AddressRepository addressRepo) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.orderStatusRepo = orderStatusRepo;
    }

    public OrderDTO getOrderById(Integer id){
        OrderEntity order = orderRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Can`t find order with id " + id));
        return orderMapper.toDTO(order);
    }

    public Page<OrderDTO> listByPage(int pageNum,
                                     Integer orderId){
        Page<OrderEntity> orderEntities;
        Pageable pageable = PageRequest.of(pageNum - 1, ORDERS_PER_PAGE);
        orderEntities = orderRepo.findAll(pageable);
        if (orderId != null){
            orderEntities = orderRepo.findAllByOrderId(orderId, pageable);
        }
        List<OrderDTO> orders = orderEntities.stream().map(orderMapper::toDTO).toList();
        return new PageImpl<>(orders, pageable, orderEntities.getTotalElements());
    }

    public OrderStatusEntity getOrCreateOrderStatus(String orderStatusName){
        return orderStatusRepo.findByOrderStatusName(orderStatusName)
                .orElseGet(()-> orderStatusRepo.save(new OrderStatusEntity(orderStatusName)));
    }

    public void saveOrder(OrderDTO orderDTO){
        OrderEntity order = orderRepo.findById(orderDTO.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Can`t find order with id " + orderDTO.getOrderId()));
        order.setOrderOrderStatus(getOrCreateOrderStatus(orderDTO.getOrderOrderStatus()));
        orderRepo.save(order);
    }

    public List<String> getOrderStatus(){
        var orderStatus = (List<OrderStatusEntity>) orderStatusRepo.findAll();
        return orderStatus.stream().map(OrderStatusEntity::getOrderStatusName).toList();
    }
}
