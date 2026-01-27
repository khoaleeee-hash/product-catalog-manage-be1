package com.example.project.service;

import com.example.project.dto.request.CategoryRequest;
import com.example.project.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);
    List<CategoryResponse> getAll();
    CategoryResponse update (Long categoryId, CategoryRequest request);
    void delete (Long categoryId);
}
