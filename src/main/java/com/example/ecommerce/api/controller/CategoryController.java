package com.example.ecommerce.api.controller;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/admin/addCategory")
    public void addCategory(@RequestHeader String categoryName) {
        categoryService.addCategory(categoryName);
    }

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/admin/deleteCategory")
    public String deleteCategory(@RequestHeader String categoryName) {
        return categoryService.removeCategory(categoryName);
    }
}
