package com.gamershop.customer.order.repo;

import com.gamershop.shared.entity.DeliveryMethodEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeliveryMethodRepository extends CrudRepository<DeliveryMethodEntity, Integer> {
    Optional<DeliveryMethodEntity> findByDeliveryMethodName(String deliveryMethodName);
}
