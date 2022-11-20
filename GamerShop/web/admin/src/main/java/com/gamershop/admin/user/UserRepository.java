package com.gamershop.admin.user;

import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity getUserEntityByUserEmail(String userEmail);

}
