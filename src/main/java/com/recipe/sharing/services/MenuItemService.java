package com.recipe.sharing.services;

import com.recipe.sharing.dto.MenuItemDTO;
import com.recipe.sharing.entities.MenuItem;
import com.recipe.sharing.repositories.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {
    private MenuItemRepository menuItemRepository;
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<MenuItemDTO> getMenuItemsByCategory(String category) {

        return menuItemRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private MenuItemDTO convertToDTO(MenuItem menuItem) {
        return new MenuItemDTO(
                menuItem.getName(),
                menuItem.getCategory(),
                menuItem.getPrice(),
                menuItem.getImage()
        );
    }
    public MenuItem saveMenuItem(MenuItemDTO menuItemDTO) {
        // Convert DTO to entity
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDTO.getName());
        menuItem.setCategory(menuItemDTO.getCategory());
        menuItem.setPrice(menuItemDTO.getPrice());

        return menuItemRepository.save(menuItem);
    }
}
