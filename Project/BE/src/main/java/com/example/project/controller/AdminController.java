package com.example.project.controller;

import com.example.project.dto.request.CategoryRequest;
import com.example.project.dto.request.ProductRequest;
import com.example.project.dto.response.ApiResponse;
import com.example.project.dto.response.CategoryResponse;
import com.example.project.dto.response.ProductResponse;
import com.example.project.service.implement.CategoryService;
import com.example.project.service.implement.ProductService;
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
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Admin", description = "Admin/Shop Owner management endpoints")
public class AdminController {

    private final CategoryService categoryService;
    private final ProductService productService;

    // ==================== Category Management ====================

    @Operation(summary = "Create a new category", description = "Admin can create a new product category")
    @PostMapping("/create-category")
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse category = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(category));
    }

    @Operation(summary = "Get all categories", description = "Admin can view all product categories")
    @GetMapping("/getAll-categories")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAll();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    @Operation(summary = "Update a category", description = "Admin can update an existing category")
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequest request) {
        CategoryResponse category = categoryService.update(categoryId, request);
        return ResponseEntity.ok(ApiResponse.success(category));
    }

    @Operation(summary = "Delete a category", description = "Admin can delete a category")
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // ==================== Product Management ====================

    @Operation(summary = "Create a new product", description = "Admin can add a new product with name, description, price, stock quantity, and category")
    @PostMapping("/create-produt")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse product = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(product));
    }

    @Operation(summary = "Get all products", description = "Admin can view all products in the system")
    @GetMapping("/getAll-products")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> products = productService.getAll();
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @Operation(summary = "Get product by ID", description = "Admin can view details of a specific product")
    @GetMapping("/getId/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long productId) {
        ProductResponse product = productService.getAll().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow();
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @Operation(summary = "Update a product", description = "Admin can edit product details")
    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request) {
        ProductResponse product = productService.update(productId, request);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @Operation(summary = "Delete a product", description = "Admin can remove a product from the catalog")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
