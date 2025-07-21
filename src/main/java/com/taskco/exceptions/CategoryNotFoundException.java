package com.taskco.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer id) {
        super("Categoria não encontrada com id: " + id);
    }
}