package com.recipe.sharing.dto;

import lombok.Data;

@Data
public class MenuItemDTO {
    private String name;
    private String category;
    private double price;
    private String image;

    public MenuItemDTO(String name, String category, double price, String image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;

    }
}
