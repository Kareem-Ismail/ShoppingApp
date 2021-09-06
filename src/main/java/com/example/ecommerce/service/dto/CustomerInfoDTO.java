package com.example.ecommerce.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoDTO {

    private String city;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String postCode;
    private String street;

}
