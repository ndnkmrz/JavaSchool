package com.gamershop.customer.product.repo;

import com.gamershop.shared.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
    Page<ProductEntity> findAllByProductCategory_CategoryIdIn(List<Integer> ids, Pageable pageable);
}
