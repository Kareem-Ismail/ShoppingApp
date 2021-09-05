package com.example.ecommerce.integration.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Category extends BaseModel {
    private String name;
    @JsonManagedReference
    @OneToMany(targetEntity = Product.class, mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> productList;
}
