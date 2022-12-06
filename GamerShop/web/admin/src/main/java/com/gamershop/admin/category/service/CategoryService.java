package com.gamershop.admin.category.service;

import com.gamershop.admin.category.interfaces.ICategoryService;
import com.gamershop.admin.category.mapper.CategoryMapper;
import com.gamershop.admin.category.repo.CategoryRepository;
import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.CategoryEntity;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public void saveCategory(CategoryDTO category){
        CategoryEntity categoryEntity = categoryMapper.toCategory(category);
        categoryRepo.save(categoryEntity);
    }

    public CategoryDTO getCategoryById(Integer id){
        CategoryEntity category = categoryRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Could not find any category with ID " + id));
        return categoryMapper.toDTO(category);
    }

    public List<CategoryDTO> listCategoriesUsedInForm(){
        var categoryList = (List<CategoryEntity>) categoryRepo.findAll();
        return (categoryList.stream().map(categoryMapper::toDTO).toList());
    }





}
