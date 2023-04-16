package com.anghack.backfullcourse.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.anghack.backfullcourse.entity.Category;
import com.anghack.backfullcourse.exception.ResourceNotFoundException;
import com.anghack.backfullcourse.payload.CategoryDto;
import com.anghack.backfullcourse.repository.CategoryRepo;
import com.anghack.backfullcourse.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);

        this.categoryRepo.save(category);

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        this.categoryRepo.save(category);

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> listeAllCategories() {
        List<Category> listCategory = this.categoryRepo.findAll();

        List<CategoryDto> listCategoryDtos = listCategory.stream()
                .map(category -> this.modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

        return listCategoryDtos;

    }

    @Override
    public CategoryDto detailOneCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category categoryToDelete = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        this.categoryRepo.delete(categoryToDelete);
    }

}
