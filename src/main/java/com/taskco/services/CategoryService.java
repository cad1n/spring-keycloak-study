package com.taskco.services;

import com.taskco.entity.Category;
import com.taskco.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public void deleteCategory(Integer id) {
        repository.deleteById(id);
    }
}
