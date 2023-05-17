package com.example.bank.infraestructure.db.dbo;

import com.example.bank.domain.entity.Wallet;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserDbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    //@OneToMany(mappedBy = "wallets", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<WalletDbo> wallets;
}
