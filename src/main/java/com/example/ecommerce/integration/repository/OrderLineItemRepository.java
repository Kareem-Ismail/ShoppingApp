package com.example.ecommerce.integration.repository;

import com.example.ecommerce.integration.model.OrderLineItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderLineItemRepository extends CrudRepository<OrderLineItem, Long> {
    @Modifying
    @Query("delete from OrderLineItem o where o.id=:line_item_id")
    void deleteOrderLineItemById(@Param("line_item_id") Long lineItemsId);
}
