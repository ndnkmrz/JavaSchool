package com.gamershop.admin.user.repo;

import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> getUserEntityByUserEmail(String userEmail);

}
