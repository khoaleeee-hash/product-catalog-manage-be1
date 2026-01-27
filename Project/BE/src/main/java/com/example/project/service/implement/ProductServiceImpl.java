package com.example.project.service.implement;


import com.example.project.dto.request.ProductRequest;
import com.example.project.dto.response.CategoryResponse;
import com.example.project.dto.response.ProductResponse;
import com.example.project.entity.Category;
import com.example.project.entity.Product;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ProductRepository;
import com.example.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductResponse create (ProductRequest request){
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->  new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);

        Product save = productRepository.save(product);
        return new ProductResponse(
                save.getId(),
                save.getName(),
                save.getDescription(),
                save.getPrice(),
                save.getStockQuantity(),
                new CategoryResponse(category.getName(), category.getId())
        );
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getStockQuantity(),
                        new CategoryResponse(p.getCategory().getName(), p.getCategory().getId())
                ))
                .toList();
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                new CategoryResponse(category.getName(), category.getId())
        );
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
