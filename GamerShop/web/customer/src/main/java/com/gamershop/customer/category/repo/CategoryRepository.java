package com.gamershop.customer.category.repo;

import com.gamershop.shared.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    public List<CategoryEntity> findAllByEnabledIsTrue();
    public Optional<CategoryEntity> findByCategoryIdAndEnabledIsTrue(Integer id);

}
