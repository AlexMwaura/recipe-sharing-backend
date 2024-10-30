package com.recipe.sharing.dto;

import lombok.Data;

@Data
public class MenuItemDTO {
    private String name;
    private String category;
    private Double price;
    private String imagePath;

    public MenuItemDTO(String name, String category, double price, String imagePath) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imagePath = imagePath;

    }
}
