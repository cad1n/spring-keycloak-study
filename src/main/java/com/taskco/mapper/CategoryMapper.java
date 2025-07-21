package com.taskco.mapper;

import com.taskco.dto.CategoryDTO;
import com.taskco.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = Category.class)
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);
    List<Category> toEntityList(List<CategoryDTO> categoriesDTOS);
    List<CategoryDTO> toDTOList(List<Category> categories);
}