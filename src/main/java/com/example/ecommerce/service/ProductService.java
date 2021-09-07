package com.example.ecommerce.service;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.integration.model.Product;
import com.example.ecommerce.integration.repository.ProductRepository;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.enums.ResponseMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String addProduct(ProductDTO productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDTO, Product.class);
        Product save = productRepository.save(product);
        if (save == null)
            throw new SaveEntityException(String.format(ResponseMessage.SQL_SAVING_EXCEPTION.getMessage(), Product.class.getSimpleName()));
        return ResponseMessage.SUCCESS.getMessage();
    }

    public String deleteProduct(String productName) {
        try {
            productRepository.deleteByCode(productName);
        } catch (Exception e) {
            return ResponseMessage.FAILED.getMessage();
        }
        return ResponseMessage.SUCCESS.getMessage();
    }

    public List<ProductDTO> getAllProducts() {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDTO> productDTOList = new ArrayList<>();
        Iterable<Product> all = productRepository.findAll();
        for (Product product : all) {
            if (product.getQuantity() > 0)
                productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    public String deleteAllProductsWithCategory(Category category) {
        productRepository.deleteByCategory(category);
        return ResponseMessage.SUCCESS.getMessage();
    }
}