package com.example.ecommerce.service;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.integration.repository.CategoryRepository;
import com.example.ecommerce.integration.repository.ProductRepository;
import com.example.ecommerce.service.enums.ResponseMessage;
import liquibase.pro.packaged.C;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ProductService productService;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void testAddCategory() {
        when(categoryRepository.save(ArgumentMatchers.any())).thenReturn(new Category());
        String response = categoryService.addCategory("Motorcycles");
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), response);
    }

    @Test
    public void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(prepareCategoriesResponse());
        List<Category> allCategories = categoryService.getAllCategories();
        Assert.assertEquals(1, allCategories.size());
    }

    @Test
    public void testRemoveCategory() {
        Category category = prepareCategory();
        when(categoryRepository.findCategoryByName(ArgumentMatchers.anyString())).thenReturn(category);
        when(productService.deleteAllProductsWithCategory(category)).thenReturn(ResponseMessage.SUCCESS.getMessage());
        String response = categoryService.removeCategory(category.getName());
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), response);
    }

    private List<Category> prepareCategoriesResponse() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        return categories;
    }

    private Category prepareCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Motorcycles");
        return category;
    }

}
