package com.example.ecommerce.integration.model;


import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "order_line_item")
public class OrderLineItem extends BaseModel{

    private Integer quantity;
    @OneToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
    private Product product;
    @OneToOne(targetEntity = CustomerOrderOrderLineItem.class, cascade = CascadeType.ALL)
    private CustomerOrderOrderLineItem customerOrderOrderLineItem;

}
