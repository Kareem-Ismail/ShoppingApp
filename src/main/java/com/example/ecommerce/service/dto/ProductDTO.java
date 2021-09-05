package com.example.ecommerce.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String description;
    private String code;
    private Double price;
    private Integer quantity;
    private Integer categoryId;
}