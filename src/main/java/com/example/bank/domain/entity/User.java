package com.example.bank.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;

// DOMAIN MODEL

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // ver si se puede quitar del domain
    private Long id;
    private String email;
    private String password;

}
