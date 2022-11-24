package com.gamershop.admin.user.repo;

import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {

    Optional<UserEntity> getUserEntityByUserEmail(String userEmail);

}
