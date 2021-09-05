package com.example.ecommerce.integration.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Admin  {
    @Id
    @Column(name = "user_name")
    private String userName;
    private String password;
    @JsonManagedReference
    @OneToMany(targetEntity = AdminRole.class, mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AdminRole> adminRole;
}
