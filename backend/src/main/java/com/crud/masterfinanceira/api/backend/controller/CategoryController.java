package com.crud.masterfinanceira.api.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.crud.masterfinanceira.api.backend.event.ResourceEvent;
import com.crud.masterfinanceira.api.backend.model.Category;
import com.crud.masterfinanceira.api.backend.repository.CategoryRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    
    private CategoryRepository categoryRepository;

    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category, HttpServletResponse response) {
        Category  categorySave = categoryRepository.save(category);
        publisher.publishEvent(new ResourceEvent(this, response, categorySave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getIdCategory(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.isPresent() ?  ResponseEntity.ok(category.get()) : ResponseEntity.notFound().build();
    }



    
}
