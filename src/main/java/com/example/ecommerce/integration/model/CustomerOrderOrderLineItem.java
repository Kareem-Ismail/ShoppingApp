package com.example.ecommerce.integration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "customer_order_order_line_item")
@IdClass(CustomerOrderOrderLineItemPK.class)
public class CustomerOrderOrderLineItem implements Serializable {

    @Id
    @JsonBackReference
    @ManyToOne(optional = false, targetEntity = CustomerOrder.class)
    @JoinColumn(name = "customer_order_id", referencedColumnName = "id")
    private CustomerOrder customerOrder;
    @Id
    @JsonManagedReference
    @OneToOne(optional = false, targetEntity = OrderLineItem.class)
    @JoinColumn(name = "line_items_id", referencedColumnName = "id")
    private OrderLineItem lineItems;

}
