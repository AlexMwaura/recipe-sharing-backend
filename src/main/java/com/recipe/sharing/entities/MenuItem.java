package com.recipe.sharing.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private double price;
    private String imagePath; // Store the path to the image file instead of the base64 data
    private String ingredients;
    @Lob
    private String method;


}
