package com.recipe.sharing.repositories;

import com.recipe.sharing.entities.Testimonials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestimonialsRepository extends JpaRepository<Testimonials, Long> {
    Testimonials findByName(String name);
}
