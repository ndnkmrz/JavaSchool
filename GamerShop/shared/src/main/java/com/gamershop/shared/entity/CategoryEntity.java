package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="Category")

public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(length = 100, nullable = false, unique = true)
    private String categoryName;

    private boolean enabled = true;

    @ManyToOne
    @JoinColumn(name="parent_id", referencedColumnName = "categoryId")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<CategoryEntity> children;
    public CategoryEntity(Integer categoryId, String categoryName, boolean enabled) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.enabled = enabled;

    }


    public CategoryEntity() {
    }

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }
}
