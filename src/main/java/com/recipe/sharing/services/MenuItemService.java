package com.recipe.sharing.services;

import com.recipe.sharing.dto.MenuItemDTO;
import com.recipe.sharing.dto.UpdateRecipeDTO;
import com.recipe.sharing.entities.MenuItem;
import com.recipe.sharing.repositories.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {
    private MenuItemRepository menuItemRepository;
//    private final Path uploadPath;


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
                menuItem.getImagePath(),
                menuItem.getIngredients(),
                menuItem.getMethod()
        );
    }
    public MenuItem saveMenuItem(MenuItemDTO menuItemDTO ,MultipartFile imageFile) {
        // Convert DTO to entity
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDTO.getName());
        menuItem.setCategory(menuItemDTO.getCategory());
        menuItem.setPrice(menuItemDTO.getPrice());
        // Instead of saving the image file, use the URL from the DTO
        if (menuItemDTO.getImagePath() != null) {
            menuItem.setImagePath(menuItemDTO.getImagePath()); // Use the URL directly
        }

        return menuItemRepository.save(menuItem);
    }
    public MenuItemDTO getMenuItemByName(String name) {
        MenuItem menuItem = menuItemRepository.findByName(name);
        return convertToDTO(menuItem);
    }
    public MenuItemDTO updateRecipeDetails(String name, UpdateRecipeDTO updateRecipeDTO) {
        MenuItem menuItem = menuItemRepository.findByName(name);
        menuItem.setIngredients(String.valueOf(updateRecipeDTO.getIngredients()));
        menuItem.setMethod(updateRecipeDTO.getMethod());
        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        return convertToDTO(updatedMenuItem);
    }

}
