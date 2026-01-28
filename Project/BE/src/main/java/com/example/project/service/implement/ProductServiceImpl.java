package com.example.project.service.implement;


import com.example.project.dto.request.ProductRequest;
import com.example.project.dto.response.CategoryResponse;
import com.example.project.dto.response.ProductResponse;
import com.example.project.entity.Category;
import com.example.project.entity.Product;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ProductRepository;
import com.example.project.service.FileStorageService;
import com.example.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final FileStorageService fileStorageService;

    @Override
    public ProductResponse createProduct (ProductRequest request, MultipartFile imageFile) {
        String imageUrl = fileStorageService.saveImage(imageFile);
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->  new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(category);
        product.setImageUrl(imageUrl);

        Product save = productRepository.save(product);
        return new ProductResponse(
                save.getId(),
                save.getName(),
                save.getDescription(),
                save.getPrice(),
                save.getStockQuantity(),
                save.getImageUrl(),
                new CategoryResponse(category.getName(), category.getId())
        );
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getStockQuantity(),
                        p.getImageUrl(),
                        new CategoryResponse(p.getCategory().getName(), p.getCategory().getId())
                ))
                .toList();
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request,MultipartFile imageFile) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        String imageUrl = fileStorageService.updateImage(product.getImageUrl(),  imageFile);

        product.setName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setImageUrl(imageUrl);
        product.setCategory(category);

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImageUrl(),
                new CategoryResponse(category.getName(), category.getId())
        );
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
