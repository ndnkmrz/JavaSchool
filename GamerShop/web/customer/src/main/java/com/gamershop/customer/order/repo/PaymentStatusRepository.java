package com.gamershop.customer.order.repo;

import com.gamershop.shared.entity.OrderStatusEntity;
import com.gamershop.shared.entity.PaymentStatusEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentStatusRepository extends CrudRepository<PaymentStatusEntity, Integer> {

    Optional<PaymentStatusEntity> findByPaymentStatusName(String paymentStatusName);
}
