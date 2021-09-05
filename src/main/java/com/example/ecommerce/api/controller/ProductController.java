package com.example.ecommerce.api.controller;

import com.example.ecommerce.api.enums.View;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/addProduct")
    public String addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @DeleteMapping("/admin/deleteProduct")
    public String deleteProduct(@RequestHeader Long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/getAllProducts")
    public String getAllProducts(Model model) {
        List<ProductDTO> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return View.PRODUCTS.getViewName();
    }
}
