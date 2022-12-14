package com.gamershop.customer.customer.repo;

import com.gamershop.shared.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleName(String roleName);
}
