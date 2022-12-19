package com.gamershop.admin.order.repo;

import com.gamershop.shared.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findCustomerEntityByUser_UserId(Integer id);
}
