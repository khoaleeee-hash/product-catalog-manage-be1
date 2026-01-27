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

    @Operation(summary = "Get all products", description = "Admin can view all products in the admin board")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> products = productService.getAll();
        return ResponseEntity.ok(ApiResponse.success(products));
    }

}
