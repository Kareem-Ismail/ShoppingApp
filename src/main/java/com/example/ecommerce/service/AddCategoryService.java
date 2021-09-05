package com.example.ecommerce.service;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.integration.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        category.setLastUpdate(new Date(System.currentTimeMillis()));
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Iterable<Category> all = categoryRepository.findAll();
        for (Category category : all)
            categories.add(category);
        return categories;
    }
}
