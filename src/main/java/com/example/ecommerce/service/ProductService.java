package com.example.ecommerce.service;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.integration.model.Product;
import com.example.ecommerce.integration.repository.ProductRepository;
import com.example.ecommerce.service.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String addProduct (ProductDTO productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDTO, Product.class);
        product.setLastUpdate(new Date(System.currentTimeMillis()));
        try {
        productRepository.save(product);
        }
        catch (Exception x) {
            return x.getMessage();
        }
        return "Successful";
    }

    public String deleteProduct(String productName) {
        try {
        productRepository.deleteByCode(productName);
        }
        catch (Exception e) {
            return "Failed";
        }
        return "Succeeded";
    }

    public List<ProductDTO> getAllProducts() {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDTO> productDTOList = new ArrayList<>();
        Iterable<Product> all = productRepository.findAll();
        for (Product product : all) {
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    public void deleteAllProductsWithCategory(Category category) {
        productRepository.deleteByCategory(category);
    }
}