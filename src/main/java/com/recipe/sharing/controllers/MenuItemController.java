package com.recipe.sharing.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.sharing.dto.CategoryDTO;
import com.recipe.sharing.dto.MenuItemDTO;
import com.recipe.sharing.dto.UpdateRecipeDTO;
import com.recipe.sharing.entities.MenuItem;
import com.recipe.sharing.services.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuItemController {
    private final MenuItemService menuItemService;
    private final ObjectMapper objectMapper;  // Add this

    public MenuItemController(MenuItemService menuItemService, ObjectMapper objectMapper) {
        this.menuItemService = menuItemService;
        this.objectMapper = objectMapper;
    }
    // Retrieve all menu items using POST
    @PostMapping("/items")
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }
    //add items in the db
    @PostMapping("/addItems")
    public MenuItem addMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        return menuItemService.saveMenuItem(menuItemDTO, null); // No file upload
    }

    // Retrieve menu items by category using POST
    @PostMapping("/items/category")
    public List<MenuItemDTO> getMenuItemsByCategory(@RequestBody CategoryDTO categoryDTO) {
        return menuItemService.getMenuItemsByCategory(categoryDTO.getCategory());
    }
    @GetMapping("/items/{name}")
    public MenuItemDTO getRecipeByName(@PathVariable("name") String name) {
        return menuItemService.getMenuItemByName(name);
    }
    @PutMapping("/items/{name}")
    public MenuItemDTO updateRecipeDetails(
            @PathVariable("name") String name,
            @RequestBody UpdateRecipeDTO updateRecipeDTO
    ) {
        return menuItemService.updateRecipeDetails(name, updateRecipeDTO);
    }
}
