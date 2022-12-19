package com.gamershop.admin.order.repo;

import com.gamershop.shared.entity.OrderProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderProductRepository extends CrudRepository<OrderProductEntity, Integer> {
    List<OrderProductEntity> findAllByOrderProductOrder_OrderDateBetween(Date start, Date end);
}
