package com.anghack.backfullcourse.service;

import java.util.List;

import com.anghack.backfullcourse.payload.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    List<CategoryDto> listeAllCategories();

    CategoryDto detailOneCategory(Integer categoryId);

    void deleteCategory(Integer categoryId);
}
