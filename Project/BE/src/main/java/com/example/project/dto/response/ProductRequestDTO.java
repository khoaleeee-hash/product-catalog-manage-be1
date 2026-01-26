package com.example.project.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductRequestDTO {
    @NotBlank(message = "Product name is required")
    private String productName;

    private String description;

    @NotNull
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotNull
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotNull
    private Long categoryId;
}
