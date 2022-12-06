package com.gamershop.admin.category.interfaces;

import com.gamershop.shared.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> listCategories();
    Page<CategoryDTO> listByPage(int pageNum,
                                 String sortField,
                                 String sortDir,
                                 String keyword);

    void saveCategory(CategoryDTO category);
    CategoryDTO getCategoryById(Integer id);
    List<CategoryDTO> listCategoriesUsedInForm();

}
