package com.gamershop.customer.order.repo;

import com.gamershop.shared.entity.OrderEntity;
import com.gamershop.shared.entity.PaymentStatusEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
}
