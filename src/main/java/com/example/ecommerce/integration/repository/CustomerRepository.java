package com.example.ecommerce.integration.repository;

import com.example.ecommerce.integration.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerByEmail(String email);
}
