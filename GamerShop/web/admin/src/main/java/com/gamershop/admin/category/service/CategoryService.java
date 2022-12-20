package com.gamershop.admin.category.service;

import com.gamershop.admin.category.interfaces.ICategoryService;
import com.gamershop.shared.mapper.CategoryMapper;
import com.gamershop.admin.category.repo.CategoryRepository;
import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.entity.CategoryEntity;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;
    public static final int CATEGORIES_PER_PAGE = 5;


    public CategoryService(CategoryRepository categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> listCategories(){
        var categoryList = (List<CategoryEntity>) categoryRepo.findAll();
        return categoryList.stream().map(categoryMapper::toDTO).toList();
    }

    public Page<CategoryDTO> listByPage(int pageNum,
                                        String sortField,
                                        String sortDir,
                                        String keyword){
        Page<CategoryEntity> categoryEntities;
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum -1, CATEGORIES_PER_PAGE, sort);
        if (keyword != null){
            keyword = "%" + keyword + "%";
            categoryEntities = categoryRepo.getCategoryEntitiesByCategoryNameLike(keyword, pageable);
        }
        else {
            categoryEntities = categoryRepo.findAll(pageable);
        }
        List<CategoryDTO> categories = categoryEntities.stream().map(categoryMapper::toDTO).toList();
        return new PageImpl<>(categories, pageable, categoryEntities.getTotalElements());
    }
    @Transactional
    public void saveCategory(CategoryDTO category){
        CategoryEntity categoryEntity = categoryMapper.toCategory(category);
        if (!category.getParentCategory().isEmpty()){
            categoryEntity.setParent(getOrCreateCategory(category.getParentCategory()));
        }
        categoryRepo.save(categoryEntity);
    }
    @Transactional
    public CategoryEntity getOrCreateCategory(String categoryName){
        return categoryRepo.findByCategoryName(categoryName).orElseGet(()-> categoryRepo.save(new CategoryEntity(categoryName)));
    }

    public CategoryDTO getCategoryById(Integer id){
        CategoryEntity category = categoryRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Could not find any category with ID " + id));
        return categoryMapper.toDTO(category);
    }

    public List<String> listCategoriesUsedInForm(){
        var categoryList = (List<CategoryEntity>) categoryRepo.findAll();
        return categoryList.stream().map(CategoryEntity::getCategoryName).toList();
    }





}
