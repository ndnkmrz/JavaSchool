package com.gamershop.shared.dto;

import com.gamershop.shared.entity.CategoryEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Integer categoryId;
    private String categoryName;
    private boolean enabled;
}
