package com.example.ecommerce.api.controller;

import com.example.ecommerce.api.enums.View;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam Map<String, Object> productMapValues) {
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(productMapValues, ProductDTO.class);
        productService.addProduct(productDTO);
        return View.ADD_PRODUCT.getViewName();
    }

    @GetMapping("/addProduct")
    public String addProductPage() {
        return View.ADD_PRODUCT.getViewName();
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam String productId) {
        productService.deleteProduct((long)Integer.parseInt(productId));
        return View.PRODUCTS_REDIRECT.getViewName();
    }

    @GetMapping("/getAllProducts")
    public String getAllProducts(Model model) {
        List<ProductDTO> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return View.PRODUCTS.getViewName();
    }
}