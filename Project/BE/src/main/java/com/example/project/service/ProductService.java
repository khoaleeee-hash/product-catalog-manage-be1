package com.example.project.service;

import com.example.project.dto.request.ProductRequest;
import com.example.project.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

<<<<<<< Updated upstream
    ProductResponse create (ProductRequest request);
    List<ProductResponse> getAll();
    ProductResponse update(Long id, ProductRequest request);
    void delete (Long id);
=======
    ProductResponse createProduct (ProductRequest request);
    List<ProductResponse> getAllProduct();
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct (Long id);
    List<ProductResponse> search(String keyword);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes

}
