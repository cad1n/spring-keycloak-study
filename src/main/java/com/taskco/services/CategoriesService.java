package com.taskco.services;

import com.taskco.entity.Categories;
import com.taskco.repositories.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository repository;

    public CategoriesService(CategoriesRepository repository) {
        this.repository = repository;
    }

    public List<Categories> findAll() {
        return repository.findAll();
    }

    public Categories findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

}
