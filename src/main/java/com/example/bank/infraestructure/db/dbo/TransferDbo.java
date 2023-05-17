package com.example.bank.infraestructure.db.dbo;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transfers")
public class TransferDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @ManyToOne()
    @JoinColumn(name = "destination_wallet_id")
    private WalletDbo destinationWallet;

    @ManyToOne()
    @JoinColumn(name = "origin_wallet_id")
    private WalletDbo originWallet;
}
