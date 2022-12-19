package com.gamershop.admin.order.repo;

import com.gamershop.shared.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
}
