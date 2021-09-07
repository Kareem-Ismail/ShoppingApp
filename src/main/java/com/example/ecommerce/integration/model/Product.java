package com.example.ecommerce.integration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Product extends BaseModel {
    private String code;
    private String description;
    private Double price;
    private Integer quantity;
    @JsonBackReference
    @ManyToOne(optional = false, targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
