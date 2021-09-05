package com.example.ecommerce.integration.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Customer extends BaseModel  {

    private String city;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    @Column(name = "post_code")
    private String postCode;
    private String street;
    @OneToMany(targetEntity = CustomerOrder.class, mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrderList;
    @OneToMany(targetEntity = Payment.class, mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> paymentList;
}
