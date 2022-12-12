package com.gamershop.customer.category.service;
import com.gamershop.customer.category.interfaces.ICategoryService;
import com.gamershop.customer.category.mapper.CategoryMapper;
import com.gamershop.customer.category.repo.CategoryRepository;
import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> listNoChildrenCategories(){
        var categoryEnableList = (List<CategoryEntity>) categoryRepo.getCategoryEntitiesByEnabledIsTrueOrderByCategoryNameAsc();
        List<CategoryEntity> categoryNoChildrenList = new ArrayList<>();

        categoryEnableList.forEach(category -> {
            List<CategoryEntity> children = category.getChildren();
            if (children == null || children.size() == 0){
                categoryNoChildrenList.add(category);
            }
        });
        return categoryNoChildrenList.stream().map(categoryMapper::toDTO).toList();

    }
}
