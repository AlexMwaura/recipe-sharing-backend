package com.recipe.sharing.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String category;

    // Constructor
    public CategoryDTO() {}

    public CategoryDTO(String category) {
        this.category = category;
    }
}
