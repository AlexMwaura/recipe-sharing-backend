package com.recipe.sharing.controllers;

import com.recipe.sharing.dto.CategoryDTO;
import com.recipe.sharing.dto.MenuItemDTO;
import com.recipe.sharing.entities.MenuItem;
import com.recipe.sharing.services.MenuItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuItemController {
    private final MenuItemService menuItemService;
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }
    // Retrieve all menu items using POST
    @PostMapping("/items")
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }
    //add items in the db
    @PostMapping("/addItems")
    public MenuItem createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        return menuItemService.saveMenuItem(menuItemDTO);
    }

    // Retrieve menu items by category using POST
    @PostMapping("/items/category")
    public List<MenuItemDTO> getMenuItemsByCategory(@RequestBody CategoryDTO categoryDTO) {
        return menuItemService.getMenuItemsByCategory(categoryDTO.getCategory());
    }
}
