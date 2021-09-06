package com.example.ecommerce.integration.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
    @JsonManagedReference
    @OneToMany(targetEntity = CustomerOrder.class, mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrderList;
    @JsonManagedReference
    @OneToMany(targetEntity = Payment.class, mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> paymentList;
}
