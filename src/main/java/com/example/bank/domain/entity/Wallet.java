package com.example.bank.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

// DOMAIN MODEL

@Data
@NoArgsConstructor
public class Wallet {

    private Long id;
    private Double balance;
    private User user;

    public void withdrawal(Double amount){
        if (balance >= amount){
            balance -= amount;
        }
    }

    public void deposit(Double amount){
        if (amount >= 0){
            balance += amount;
        }
    }
}
