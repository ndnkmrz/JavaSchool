package com.gamershop.admin.user;

import com.gamershop.shared.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
