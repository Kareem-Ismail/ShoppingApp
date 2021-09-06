package com.example.ecommerce.api.controller;

import com.example.ecommerce.integration.model.Customer;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.service.dto.CustomerInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/addNewCustomer")
    public String addNewCustomer(@RequestBody CustomerInfoDTO customerInfoDto) {
        return customerService.addNewCustomer(customerInfoDto);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}
