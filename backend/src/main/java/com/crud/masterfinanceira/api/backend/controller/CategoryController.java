package com.crud.masterfinanceira.api.backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.crud.masterfinanceira.api.backend.model.Category;
import com.crud.masterfinanceira.api.backend.repository.CategoryRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
        
    }
    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category, HttpServletResponse response) {
        Category  categorySave = categoryRepository.save(category);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(categorySave.getId()).toUri();

        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categorySave);
    }

    @GetMapping("/{id}")
    public Optional<Category> getIdCategory(@PathVariable Long id) {
        return categoryRepository.findById(id);
    }
}
