package com.gamershop.admin.category.repo;

import com.gamershop.shared.entity.CategoryEntity;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {

    Page<CategoryEntity> getCategoryEntitiesByCategoryNameLike(String keyword, Pageable pageable);
}
