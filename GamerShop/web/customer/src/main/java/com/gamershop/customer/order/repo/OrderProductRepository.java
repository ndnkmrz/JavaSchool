package com.gamershop.customer.order.repo;

import com.gamershop.shared.entity.OrderProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProductEntity, Integer> {
}
