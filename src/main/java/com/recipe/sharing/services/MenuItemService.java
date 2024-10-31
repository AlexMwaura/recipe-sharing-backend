package com.recipe.sharing.services;

import com.recipe.sharing.dto.MenuItemDTO;
import com.recipe.sharing.dto.UpdateRecipeDTO;
import com.recipe.sharing.entities.MenuItem;
import com.recipe.sharing.repositories.MenuItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {
    private MenuItemRepository menuItemRepository;
    private final Path uploadPath;


    public MenuItemService(MenuItemRepository menuItemRepository) {

        this.menuItemRepository = menuItemRepository;
        // Create an absolute path in the user's home directory
        this.uploadPath = Paths.get(System.getProperty("user.home"), "recipe-uploads", "images");
        // Create directories if they don't exist
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
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
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String filePath = saveImage(imageFile);
                menuItem.setImagePath(filePath);
            } catch (IOException e) {
                // Handle the exception, e.g., log it and throw a runtime exception
                throw new RuntimeException("Failed to save image", e);
            }
        }

        return menuItemRepository.save(menuItem);
    }
    private String saveImage(MultipartFile imageFile) throws IOException {
        // Generate file name
        String filename = System.currentTimeMillis() + "_" +
                StringUtils.cleanPath(imageFile.getOriginalFilename());

        // Create the complete path
        Path destinationFile = uploadPath.resolve(filename);

        // Copy the file to the destination
        try {
            Files.copy(imageFile.getInputStream(), destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + filename, e);
        }

        return "/images/" + filename;
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
