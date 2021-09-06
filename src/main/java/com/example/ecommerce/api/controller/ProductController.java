package com.example.ecommerce.api.controller;

import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return "product added";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam String productId) {
        productService.deleteProduct((long)Integer.parseInt(productId));
        return "Succeeded";
    }

    @GetMapping("/getAllProducts")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }
}