package com.gamershop.customer.customer.repo;

import com.gamershop.shared.entity.CustomerEntity;
import com.gamershop.shared.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findCustomerEntityByCustomerUserId(Integer id);
}
