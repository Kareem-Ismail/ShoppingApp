package com.example.ecommerce.service;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.integration.model.Product;
import com.example.ecommerce.integration.repository.ProductRepository;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.enums.ResponseMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(prepareProductsList());
        List<ProductDTO> allProducts = productService.getAllProducts();
        Assert.assertEquals(1, allProducts.size());
    }

    @Test
    public void testDeleteProduct() {
        String response = productService.deleteProduct("Product");
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), response);
    }

    @Test
    public void testAddProduct() {
        when(productRepository.save(ArgumentMatchers.any())).thenReturn(new Product());
        String response = productService.addProduct(new ProductDTO());
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), response);
    }

    @Test
    public void deleteAllProducts() {
        String response = productService.deleteAllProductsWithCategory(new Category());
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), response);
    }

    private List<Product> prepareProductsList() {
        Product product = new Product();
        product.setCode("Harley Davidson");
        product.setQuantity(10);
        return new ArrayList<>(Collections.singletonList(product));
    }

}
