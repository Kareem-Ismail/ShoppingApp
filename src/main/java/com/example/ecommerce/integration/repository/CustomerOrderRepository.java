package com.example.ecommerce.integration.repository;

import com.example.ecommerce.integration.model.Customer;
import com.example.ecommerce.integration.model.CustomerOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {
    CustomerOrder findCustomerOrderByCustomer(Customer customer);
    @Modifying
    @Query("delete from CustomerOrder c where c.id = :customer_order_id")
    void deleteCustomerOrder(@Param("customer_order_id") Long id);
}
