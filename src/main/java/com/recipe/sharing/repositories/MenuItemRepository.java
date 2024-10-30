package com.recipe.sharing.repositories;

import com.recipe.sharing.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategory(String category); // To filter items by category if needed.
}
