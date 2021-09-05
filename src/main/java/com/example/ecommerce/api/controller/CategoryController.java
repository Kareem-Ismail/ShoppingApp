package com.example.ecommerce.api.controller;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.service.AddCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    AddCategoryService addCategoryService;

    @PostMapping("/addCategory")
    public void addCategory(@RequestHeader String categoryName) {
        addCategoryService.addCategory(categoryName);
    }

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories() {
        return addCategoryService.getAllCategories();
    }
}
