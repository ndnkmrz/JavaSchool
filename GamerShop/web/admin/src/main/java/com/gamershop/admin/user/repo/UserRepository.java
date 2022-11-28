package com.gamershop.admin.user.repo;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> getUserEntityByUserEmail(String userEmail);



    public Page<UserEntity> findAllByUserNameLikeOrUserEmailLike(String userName, String userEmail, Pageable pageable);



}
