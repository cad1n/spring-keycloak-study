package com.taskco.controllers;

import com.taskco.dto.CategoryDTO;
import com.taskco.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        CategoryController categoryController = new CategoryController(categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategories_returnsOkWithList() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        Mockito.when(categoryService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getAllCategories_returnsNoContent() throws Exception {
        Mockito.when(categoryService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getCategoryById_found() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        Mockito.when(categoryService.findById(1)).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getCategoryById_notFound() throws Exception {
        Mockito.when(categoryService.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createCategory_success() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        Mockito.when(categoryService.saveCategory(any())).thenReturn(Optional.of(dto));

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Teste\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void createCategory_badRequest() throws Exception {
        Mockito.when(categoryService.saveCategory(any())).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Teste\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateCategory_success() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        Mockito.when(categoryService.updateCategory(any())).thenReturn(Optional.of(dto));

        mockMvc.perform(put("/api/categories/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Atualizado\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void updateCategory_badRequest() throws Exception {
        Mockito.when(categoryService.updateCategory(any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/categories/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Atualizado\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteCategoryById_success() throws Exception {
        Mockito.doNothing().when(categoryService).deleteCategory(1);

        mockMvc.perform(delete("/api/categories/1/delete"))
                .andExpect(status().isOk());
    }
}