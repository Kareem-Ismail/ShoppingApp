package com.example.ecommerce.integration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customer_order")
public class CustomerOrder extends BaseModel {
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "order_amount")
    private Double orderAmount;
    @JsonBackReference
    @ManyToOne(optional = false, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @JsonManagedReference
    @OneToMany(targetEntity = CustomerOrderOrderLineItem.class, mappedBy = "customerOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerOrderOrderLineItem> customerOrderOrderLineItemList;
}
