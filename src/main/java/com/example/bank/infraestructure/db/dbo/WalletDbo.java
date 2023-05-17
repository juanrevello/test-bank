package com.example.bank.infraestructure.db.dbo;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wallets")
public class WalletDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserDbo user;
}
