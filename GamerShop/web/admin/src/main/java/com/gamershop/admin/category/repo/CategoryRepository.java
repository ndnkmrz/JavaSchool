package com.gamershop.admin.category.repo;

import com.gamershop.shared.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {

    Page<CategoryEntity> getCategoryEntitiesByCategoryNameLike(String keyword, Pageable pageable);

    Optional<CategoryEntity> findByCategoryName(String categoryName);
}
