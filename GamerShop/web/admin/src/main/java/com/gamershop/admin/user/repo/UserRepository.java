package com.gamershop.admin.user.repo;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> getUserEntityByUserEmail(String userEmail);


    @Query("SELECT u FROM UserEntity u WHERE u.userName LIKE %?1%")
    public Page<UserEntity> findAll(String keyword, Pageable pageable);






}
