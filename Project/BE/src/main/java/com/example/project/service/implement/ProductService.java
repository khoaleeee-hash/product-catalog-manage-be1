package com.example.project.service.implement;

import com.example.project.dto.request.ProductRequest;
import com.example.project.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create (ProductRequest request);
    List<ProductResponse> getAll();
    ProductResponse update(Long id, ProductRequest request);
    void delete (Long id);

}
