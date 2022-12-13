package com.gamershop.customer.category.interfaces;


import com.gamershop.shared.dto.CategoryDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICategoryService {
    Map<CategoryDTO, Set<CategoryDTO>> listWithChildrenCategories();
    CategoryDTO getCategoryById(Integer id);
    List<CategoryDTO> getCategoryParentsById(Integer id);

}
