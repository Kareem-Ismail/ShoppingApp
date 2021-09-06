package com.example.ecommerce.integration.repository;

import com.example.ecommerce.integration.model.CustomerOrderOrderLineItem;
import com.example.ecommerce.integration.model.CustomerOrderOrderLineItemPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerOrderOrderLineItemRepository extends CrudRepository<CustomerOrderOrderLineItem, CustomerOrderOrderLineItemPK> {
    @Modifying
    @Query("delete from CustomerOrderOrderLineItem b where b.lineItems.id=:line_items_id AND b.customerOrder.id = :customer_order_id")
    void deleteCustomerOrderOrderLineItems(@Param("line_items_id") Long lineItemsId, @Param("customer_order_id") Long customerOrderId);
}
