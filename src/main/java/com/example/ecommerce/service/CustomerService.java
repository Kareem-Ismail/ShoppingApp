package com.example.ecommerce.service;


import com.example.ecommerce.integration.model.Customer;
import com.example.ecommerce.integration.repository.CustomerRepository;
import com.example.ecommerce.service.dto.CustomerInfoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String addNewCustomer(CustomerInfoDTO customerInfoDto) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerInfoDto, Customer.class);
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            return "Customer adding was failed";
        }
        return "Customer was added successfully";
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customersList = new ArrayList<>();
        Iterable<Customer> all = customerRepository.findAll();
        for (Customer customer : all) {
            customersList.add(customer);
        }
        return customersList;
    }
}
