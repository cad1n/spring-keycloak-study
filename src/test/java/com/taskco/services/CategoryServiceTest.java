package com.taskco.services;

import com.taskco.dto.CategoryDTO;
import com.taskco.entity.Category;
import com.taskco.exceptions.CategoryNotFoundException;
import com.taskco.mapper.CategoryMapper;
import com.taskco.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    private CategoryRepository repository;
    private CategoryMapper mapper;
    private CategoryService service;

    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        mapper = mock(CategoryMapper.class);
        service = new CategoryService(repository, mapper);
    }

    @Test
    void findAll_returnsListOfDTO() {
        Category category = new Category();
        CategoryDTO dto = new CategoryDTO();
        when(repository.findAll()).thenReturn(List.of(category));
        when(mapper.toDTO(category)).thenReturn(dto);

        List<CategoryDTO> result = service.findAll();

        assertEquals(1, result.size());
        assertSame(dto, result.getFirst());
    }

    @Test
    void findById_found_returnsDTO() {
        Category category = new Category();
        CategoryDTO dto = new CategoryDTO();
        when(repository.findById(1)).thenReturn(Optional.of(category));
        when(mapper.toDTO(category)).thenReturn(dto);

        Optional<CategoryDTO> result = service.findById(1);

        assertTrue(result.isPresent());
        assertSame(dto, result.get());
    }

    @Test
    void findById_notFound_returnsEmpty() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Optional<CategoryDTO> result = service.findById(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void updateCategory_found_returnsUpdatedDTO() {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1);
        dto.setName("Updated");
        Category existing = new Category();
        existing.setId(1);
        existing.setName("Old");
        Category updated = new Category();
        updated.setId(1);
        updated.setName("Updated");
        CategoryDTO updatedDTO = new CategoryDTO();
        updatedDTO.setId(1);
        updatedDTO.setName("Updated");

        when(repository.findById(1)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(updated);
        when(mapper.toDTO(updated)).thenReturn(updatedDTO);

        Optional<CategoryDTO> result = service.updateCategory(dto);

        assertTrue(result.isPresent());
        assertEquals("Updated", result.get().getName());
    }

    @Test
    void updateCategory_notFound_returnsEmpty() {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1);

        when(repository.findById(1)).thenReturn(Optional.empty());

        Optional<CategoryDTO> result = service.updateCategory(dto);

        assertTrue(result.isEmpty());
    }

    @Test
    void saveCategory_success_returnsDTO() {
        CategoryDTO dto = new CategoryDTO();
        Category entity = new Category();
        Category saved = new Category();
        CategoryDTO savedDTO = new CategoryDTO();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(savedDTO);

        Optional<CategoryDTO> result = service.saveCategory(dto);

        assertTrue(result.isPresent());
        assertSame(savedDTO, result.get());
    }

    @Test
    void saveCategory_exception_returnsEmpty() {
        CategoryDTO dto = new CategoryDTO();
        when(mapper.toEntity(dto)).thenThrow(new RuntimeException());

        Optional<CategoryDTO> result = service.saveCategory(dto);

        assertTrue(result.isEmpty());
    }

    @Test
    void deleteCategory_exists_deletes() {
        when(repository.existsById(1)).thenReturn(true);

        service.deleteCategory(1);

        verify(repository).deleteById(1);
    }

    @Test
    void deleteCategory_notExists_throwsException() {
        when(repository.existsById(1)).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> service.deleteCategory(1));
    }
}