package com.gamershop.customer.category.mapper;

import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.entity.CategoryEntity;
import org.springframework.stereotype.Service;


@Service
public class CategoryMapper {
    public CategoryDTO toDTO(CategoryEntity categoryEntity){
        Integer id = categoryEntity.getCategoryId();
        String categoryName = categoryEntity.getCategoryName();
        boolean enable = categoryEntity.isEnabled();
        String parentCategory = categoryEntity.getParent() != null ? categoryEntity.getParent().getCategoryName() : null;
        return new CategoryDTO(id, categoryName, enable, parentCategory);
    }

    public CategoryEntity toCategory(CategoryDTO categoryDTO){
        return new CategoryEntity(categoryDTO.getCategoryId(), categoryDTO.getCategoryName(), categoryDTO.isEnabled());
    }
}
