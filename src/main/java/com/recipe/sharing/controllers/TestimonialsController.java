package com.recipe.sharing.controllers;

import com.recipe.sharing.dto.TestimonialsResponse;
import com.recipe.sharing.entities.Testimonials;
import com.recipe.sharing.services.TestimonialsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/testimonials")
public class TestimonialsController {
    private final TestimonialsService testimonialsService;
    public TestimonialsController(TestimonialsService testimonialsService) {
        this.testimonialsService = testimonialsService;
    }
    @PostMapping ("/all")
    public List<TestimonialsResponse> getAllTestimonials() {
        return testimonialsService.getAllTestimonials();
    }
    @PostMapping("/addTestimonials")
    public Testimonials addTestimonial(@RequestBody TestimonialsResponse testimonialsDTO) {
        return testimonialsService.saveTestimonial(testimonialsDTO);
    }
}
