package com.example.ecommerce.integration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_role")
public class AdminRole {
    @Id
    @Column(name = "role_name")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleName;
    @ManyToOne(optional = false, targetEntity = Admin.class)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    @JsonBackReference
    private Admin admin;
}