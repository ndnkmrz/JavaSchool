package com.gamershop.admin.user;

import org.springframework.data.repository.CrudRepository;
import com.gamershop.shared.entity.RoleEntity;

import java.util.Optional;


public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleName(String roleName);
}
