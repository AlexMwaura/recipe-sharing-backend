package com.recipe.sharing.services;

import com.recipe.sharing.dto.TestimonialsResponse;
import com.recipe.sharing.entities.Testimonials;
import com.recipe.sharing.repositories.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonialsService {
    @Autowired
    private TestimonialsRepository testimonialsRepository;

    public TestimonialsService(TestimonialsRepository testimonialsRepository) {
        this.testimonialsRepository = testimonialsRepository;
    }
    // Fetch all testimonials
    public List<TestimonialsResponse> getAllTestimonials() {
        return testimonialsRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    // Save a new testimonial
    public Testimonials saveTestimonial(TestimonialsResponse testimonialsDTO) {
        Testimonials testimonial = new Testimonials();
        testimonial.setName(testimonialsDTO.getName());
        testimonial.setDescription(testimonialsDTO.getDescription());
        return testimonialsRepository.save(testimonial);
    }
    // Fetch a testimonial by name
    public TestimonialsResponse getTestimonialByName(String name) {
        Testimonials testimonial = testimonialsRepository.findByName(name);
        return convertToDTO(testimonial);
    }
    // Update an existing testimonial
    public TestimonialsResponse updateTestimonial(String name, TestimonialsResponse updatedTestimonialDTO) {
        Testimonials testimonial = testimonialsRepository.findByName(name);
        if (testimonial != null) {
            testimonial.setDescription(updatedTestimonialDTO.getDescription());
            Testimonials updatedTestimonial = testimonialsRepository.save(testimonial);
            return convertToDTO(updatedTestimonial);
        }
        return null;
    }

    // Convert entity to DTO
    private TestimonialsResponse convertToDTO(Testimonials testimonial) {
        return new TestimonialsResponse(testimonial.getName(), testimonial.getDescription());
    }
}
