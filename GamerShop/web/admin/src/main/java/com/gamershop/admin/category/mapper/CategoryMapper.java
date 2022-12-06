package com.gamershop.admin.category.mapper;

import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.CategoryEntity;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryMapper {
    public CategoryDTO toDTO(CategoryEntity categoryEntity){
        Integer id = categoryEntity.getCategoryId();
        String categoryName = categoryEntity.getCategoryName();
        boolean enable = categoryEntity.isEnabled();
        return new CategoryDTO(id, categoryName, enable);
    }

    public CategoryEntity toCategory(CategoryDTO categoryDTO){
        return new CategoryEntity(categoryDTO.getCategoryId(), categoryDTO.getCategoryName(), categoryDTO.isEnabled());
    }
}
