package com.example.ecommerce.integration.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "customer_order_order_line_item")
public class CustomerOrderOrderLineItem implements Serializable {

    @Id
    @ManyToOne(optional = false, targetEntity = CustomerOrder.class)
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id")
    private CustomerOrder customerOrderId;
    @Id
    @OneToOne(optional = false, targetEntity = OrderLineItem.class)
    @JoinColumn(name = "line_items_id", referencedColumnName = "id")
    private OrderLineItem lineItemsId;

}
