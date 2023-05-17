package com.example.bank.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

// DOMAIN MODEL
@Data
@NoArgsConstructor
public class Transfer {
    private Long id;
    private Double amount;
    private Wallet originWallet;
    private Wallet destinationWallet;
    //private LocalDateTime date; //configurar
}
