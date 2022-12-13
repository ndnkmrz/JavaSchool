package com.gamershop.customer.category.service;
import com.gamershop.customer.category.interfaces.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {
//    private final CategoryRepository categoryRepo;
//    private final CategoryMapper categoryMapper;
//
//    public CategoryService(CategoryRepository categoryRepo, CategoryMapper categoryMapper) {
//        this.categoryRepo = categoryRepo;
//        this.categoryMapper = categoryMapper;
//    }
//
//    public List<CategoryDTO> listNoChildrenCategories(){
//        var categoryEnableList = categoryRepo.getCategoryEntitiesByEnabledIsTrueOrderByCategoryNameAsc();
//        List<CategoryEntity> categoryNoChildrenList = new ArrayList<>();
//
//        categoryEnableList.forEach(category -> {
//            List<CategoryEntity> children = category.getChildren();
//            if (children == null || children.isEmpty()){
//                categoryNoChildrenList.add(category);
//            }
//        });
//        return categoryNoChildrenList.stream().map(categoryMapper::toDTO).toList();
//
//    }
}
