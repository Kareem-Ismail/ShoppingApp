package com.example.ecommerce.integration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "admin_role")
public class AdminRole {
    @EmbeddedId
    @Column(name = "role_name")
    private AdminRolePk roleName;
    @ManyToOne(optional = false, targetEntity = Admin.class)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    @JsonBackReference
    private Admin admin;
}