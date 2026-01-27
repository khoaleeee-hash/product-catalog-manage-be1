package com.example.project.controller;

import com.example.project.dto.request.ProductRequest;
import com.example.project.dto.response.ApiResponse;
import com.example.project.dto.response.ProductResponse;
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
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "Product management endpoints for Admin (Customer endpoints will be added separately)")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create a new product",
               description = "Admin can add a new product with name (required), description, price (required, positive), stock quantity (required, non-negative), and category")
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse product = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(product));
    }

    @Operation(summary = "Get all products (Admin)", description = "Admin can view all products in the admin board")
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProductsAdmin() {
        List<ProductResponse> products = productService.getAll();
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @Operation(summary = "Get product by ID (Admin)", description = "Admin can view details of a specific product")
    @GetMapping("/getId/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductByIdAdmin(@PathVariable Long productId) {
        ProductResponse product = productService.getAll().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow();
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @Operation(summary = "Update a product", description = "Admin can edit product details (name, description, price, stock quantity, category)")
    @PutMapping("/update/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request) {
        ProductResponse product = productService.update(productId, request);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @Operation(summary = "Delete a product", description = "Admin can remove a product from the catalog")
    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // ==================== PUBLIC/CUSTOMER ENDPOINTS (To be implemented) ====================
    // Note: Người code phần Customer có thể thêm các endpoints sau:
    // - GET /api/products - View all products (public, no authentication)
    // - GET /api/products/{id} - View product details (public)
    // - GET /api/products/category/{categoryId} - Filter by category (public)
    // - GET /api/products/search?name=... - Search products by name (public)
}
