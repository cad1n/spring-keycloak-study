package com.taskco.services;

import com.taskco.dto.CategoryDTO;
import com.taskco.entity.Category;
import com.taskco.exceptions.CategoryNotFoundException;
import com.taskco.mapper.CategoryMapper;
import com.taskco.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoryDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    public Optional<CategoryDTO> findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDTO);
    }

    public Optional<CategoryDTO> updateCategory(CategoryDTO category) {
        return repository.findById(category.getId()).map(existing -> {
            existing.setName(category.getName());
            Category updated = repository.save(existing);
            return mapper.toDTO(updated);
        });
    }

    public void saveCategory(CategoryDTO categoryDTO) {
        Category category = mapper.toEntity(categoryDTO);
        repository.save(category);
    }

    public void deleteCategory(Integer id) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
