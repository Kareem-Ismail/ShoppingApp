package com.example.ecommerce.integration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Payment extends BaseModel{

    private Double amount;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "transaction_id")
    private String transactionId;
    @ManyToOne(optional = false, targetEntity = CustomerOrder.class)
    @JoinColumn(name = "customer_order", referencedColumnName = "id")
    @JsonBackReference
    private CustomerOrder customerOrder;
    @ManyToOne(optional = false, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;

}
