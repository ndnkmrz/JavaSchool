package com.gamershop.customer.order.repo;

import com.gamershop.shared.entity.OrderStatusEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderStatusRepository extends CrudRepository<OrderStatusEntity, Integer> {

    Optional<OrderStatusEntity> findByOrderStatusName(String orderStatusName);
}
