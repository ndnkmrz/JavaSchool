package com.gamershop.customer.category.repo;
import com.gamershop.shared.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    List<CategoryEntity> getCategoryEntitiesByEnabledIsTrueOrderByCategoryNameAsc();
}
