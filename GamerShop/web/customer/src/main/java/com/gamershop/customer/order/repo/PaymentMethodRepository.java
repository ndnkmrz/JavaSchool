package com.gamershop.customer.order.repo;

import com.gamershop.shared.entity.PaymentMethodEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethodEntity, Integer> {
    Optional<PaymentMethodEntity> findByPaymentMethodName(String paymentMethodName);
}
