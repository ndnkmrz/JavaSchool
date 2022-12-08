package com.gamershop.admin.product.repo;

import com.gamershop.shared.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
public interface ProductRepo extends PagingAndSortingRepository<ProductEntity, Integer> {
    Page<ProductEntity> getProductEntitiesByProductNameLike(String keyword, Pageable pageable);

    Optional<ProductEntity> findByProductName(String productName);
}
