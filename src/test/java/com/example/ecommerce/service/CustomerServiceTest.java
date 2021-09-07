package com.example.ecommerce.service;

import com.example.ecommerce.integration.model.Customer;
import com.example.ecommerce.integration.repository.CustomerRepository;
import com.example.ecommerce.service.dto.CustomerInfoDTO;
import com.example.ecommerce.service.enums.ResponseMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void testAddCustomer() {
        when(customerRepository.save(ArgumentMatchers.any())).thenReturn(new Customer());
        String response = customerService.addNewCustomer(new CustomerInfoDTO());
        Assert.assertEquals(ResponseMessage.SUCCESS.getMessage(), response);
    }

    @Test
    public void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(prepareCustomersListResponse());
        List<Customer> allCustomers = customerService.getAllCustomers();
        Assert.assertEquals(1, allCustomers.size());
    }

    private List<Customer> prepareCustomersListResponse() {
        return Collections.singletonList(new Customer());
    }

}
