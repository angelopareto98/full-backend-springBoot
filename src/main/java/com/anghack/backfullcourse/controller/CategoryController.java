package com.anghack.backfullcourse.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anghack.backfullcourse.payload.ApiResponse;
import com.anghack.backfullcourse.payload.CategoryDto;
import com.anghack.backfullcourse.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(path = "/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {

        CategoryDto categoryCreated = this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
            @PathVariable("id") Integer id) {
        CategoryDto CategoryUpdated = this.categoryService.updateCategory(categoryDto, id);

        return new ResponseEntity<>(CategoryUpdated, HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<CategoryDto>> listAllCategories() {
        List<CategoryDto> listeAllCategoriesDto = this.categoryService.listeAllCategories();

        return new ResponseEntity<>(listeAllCategoriesDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> getOneCategory(@PathVariable("id") int idCat) {
        CategoryDto categoryDtoSelected = this.categoryService.detailOneCategory(idCat);

        return ResponseEntity.ok(categoryDtoSelected);
    }

    @DeleteMapping(path = "/{idCat}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int idCat) {

        this.categoryService.deleteCategory(idCat);

        return ResponseEntity.ok(new ApiResponse("Category deleted successfully", true));
    }

};
