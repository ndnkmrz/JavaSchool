package com.gamershop.admin.product.repo;

import com.gamershop.shared.entity.ProductImageEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProductImageRepo extends CrudRepository<ProductImageEntity, Integer> {
}
