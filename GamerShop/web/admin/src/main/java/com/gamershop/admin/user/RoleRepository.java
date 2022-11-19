package com.gamershop.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gamershop.shared.entity.Role;


public interface RoleRepository extends CrudRepository<Role, Integer> {
}
