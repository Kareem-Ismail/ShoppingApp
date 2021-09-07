package com.example.ecommerce.service;


import com.example.ecommerce.integration.model.Customer;
import com.example.ecommerce.integration.repository.CustomerRepository;
import com.example.ecommerce.service.dto.CustomerInfoDTO;
import com.example.ecommerce.service.enums.ResponseMessage;
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
        Customer save = customerRepository.save(customer);
        if (save == null)
            throw new SaveEntityException(String.format(ResponseMessage.SQL_SAVING_EXCEPTION.getMessage(), Customer.class.getSimpleName()));
        return ResponseMessage.SUCCESS.getMessage();
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
