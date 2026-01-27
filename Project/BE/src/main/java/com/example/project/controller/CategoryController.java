package com.example.project.controller;

import com.example.project.dto.request.CategoryRequest;
import com.example.project.dto.response.ApiResponse;
import com.example.project.dto.response.CategoryResponse;
import com.example.project.service.implement.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category Management", description = "Category management endpoints for Admin")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Admin can view all product categories")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAll();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }
}
