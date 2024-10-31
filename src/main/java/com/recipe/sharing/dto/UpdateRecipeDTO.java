package com.recipe.sharing.dto;

import lombok.Data;

import java.util.List;
@Data
public class UpdateRecipeDTO {
    private List<String> ingredients;
    private String method;
}
