package com.gamershop.admin.order.repo;

import com.gamershop.shared.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Page<OrderEntity> findAllByOrderId(Integer orderId, Pageable pageable);
    List<OrderEntity> findByOrderDateBetween(Date start, Date end);
}
