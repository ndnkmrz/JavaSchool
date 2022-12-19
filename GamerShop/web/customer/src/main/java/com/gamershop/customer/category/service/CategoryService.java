package com.gamershop.customer.category.service;
import com.gamershop.customer.category.interfaces.ICategoryService;
import com.gamershop.customer.category.repo.CategoryRepository;
import com.gamershop.customer.exception.CategoryNotFoundException;
import com.gamershop.shared.dto.CategoryDTO;
import com.gamershop.shared.entity.CategoryEntity;
import com.gamershop.shared.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO checkDictionaryValues(String categoryName, Set<CategoryDTO> list){
        CategoryDTO categoryDTO = null;
        for(CategoryDTO dto : list){
            if(dto.getCategoryName().equals(categoryName)){
                categoryDTO = dto;
            }
        }
        return categoryDTO;
    }

    public Map<CategoryDTO, Set<CategoryDTO>> listWithChildrenCategories(){
        var categoryEnableList = categoryRepo.findAllByEnabledIsTrue();
        CategoryDTO absent;
        Map<CategoryDTO, Set<CategoryDTO>> dictionary = new HashMap<CategoryDTO, Set<CategoryDTO>>();
        for(CategoryEntity category : categoryEnableList){
            if (category.getParent() == null){
                CategoryDTO categoryDTO = categoryMapper.toDTO(category);
                absent = checkDictionaryValues(category.getCategoryName(), dictionary.keySet());
                if(absent == null){
                    dictionary.put(categoryDTO, new HashSet<>());
                }
            }
            else{
                CategoryDTO categoryDTO = categoryMapper.toDTO(category);
                CategoryDTO parentCategory = categoryMapper.toDTO(category.getParent());
                CategoryDTO absentParent = checkDictionaryValues(parentCategory.getCategoryName(), dictionary.keySet());
                if(absentParent == null){
                    dictionary.put(parentCategory, new HashSet<>());
                }
                absent = checkDictionaryValues(category.getCategoryName(), dictionary.get(absentParent));
                if(absent == null){
                    dictionary.get(absentParent).add(categoryDTO);
                }
            }
        }
        return dictionary;
    }

    public CategoryDTO getCategoryById(Integer id){
        CategoryEntity category = categoryRepo.findByCategoryIdAndEnabledIsTrue(id)
                .orElseThrow(()-> new CategoryNotFoundException("Could not find any category with ID" + id));
        return categoryMapper.toDTO(category);
    }

    public List<CategoryDTO> getCategoryParentsById(Integer id){
        List<CategoryDTO> listParents = new ArrayList<>();
        CategoryEntity childEntity = categoryRepo.findByCategoryIdAndEnabledIsTrue(id).orElseThrow(()->
                new CategoryNotFoundException("Could not find any category with ID " + id));
        CategoryEntity parentEntity = childEntity.getParent();
        while(parentEntity != null){
            listParents.add(0, categoryMapper.toDTO(parentEntity));
            parentEntity = parentEntity.getParent();
        }
        listParents.add(categoryMapper.toDTO(childEntity));
        return listParents;
    }

    public List<Integer> getCategoryChildrenById(Integer id){
        CategoryEntity parentEntity = categoryRepo.findByCategoryIdAndEnabledIsTrue(id).orElseThrow(()->
                new CategoryNotFoundException("Could not find any category with ID " + id));
        List<CategoryEntity> children = parentEntity.getChildren();
        List<Integer> child = children.isEmpty() ? new ArrayList<>() : new ArrayList<>(children.stream()
                .map(CategoryEntity::getCategoryId).toList());
        child.add(id);
        return child;
    }

    public List<Integer> getAllCategoryId(){
        List<CategoryEntity> categoryList = (List<CategoryEntity>) categoryRepo.findAll();
        return categoryList.stream().map(CategoryEntity::getCategoryId).toList();
    }
}

