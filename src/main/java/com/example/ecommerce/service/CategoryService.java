package com.example.ecommerce.service;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.integration.repository.CategoryRepository;
import com.example.ecommerce.service.exception.SaveEntityException;
import com.example.ecommerce.service.enums.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    public String addCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        Category save = categoryRepository.save(category);
        if (save == null)
            throw new SaveEntityException(String.format(ResponseMessage.SQL_SAVING_EXCEPTION.getMessage(), Category.class.getSimpleName()));
        return ResponseMessage.SUCCESS.getMessage();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Iterable<Category> all = categoryRepository.findAll();
        for (Category category : all)
            categories.add(category);
        return categories;
    }

    public String removeCategory(String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName);
        try {
            String s = productService.deleteAllProductsWithCategory(category);
            if (s.equals(ResponseMessage.SUCCESS.getMessage()))
                categoryRepository.deleteById(category.getId());
        } catch (Exception e) {
            return "Failed to delete the category or category doesn't exist!";
        }
        return ResponseMessage.SUCCESS.getMessage();
    }
}
