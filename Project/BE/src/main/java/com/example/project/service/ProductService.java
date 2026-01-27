package com.example.project.service;

import com.example.project.dto.request.ProductRequest;
import com.example.project.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct (ProductRequest request);
    List<ProductResponse> getAllProduct();
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct (Long id);
    List<ProductResponse> search(String keyword);


}
