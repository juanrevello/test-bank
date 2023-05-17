package com.example.bank.infraestructure.db.mapper;

import com.example.bank.domain.entity.User;
import com.example.bank.domain.entity.Wallet;
import com.example.bank.infraestructure.db.dbo.WalletDbo;


public class WalletDBMapper {

    public static Wallet dboToDomain(WalletDbo walletDbo) {
        Wallet wallet = new Wallet();
        wallet.setId(walletDbo.getId());
        wallet.setBalance(walletDbo.getBalance());
        wallet.setUser(UserDBMapper.dboToDomain(walletDbo.getUser()));
        return wallet;
    }

    public static WalletDbo domainToDbo(Wallet wallet, User user) {
        WalletDbo walletDbo = new WalletDbo();
        walletDbo.setId(wallet.getId());
        walletDbo.setBalance(wallet.getBalance());
        walletDbo.setUser(UserDBMapper.domainToDbo(user));
        return walletDbo;
    }

    public static WalletDbo domainToDbo(Wallet wallet) {
        WalletDbo walletDbo = new WalletDbo();
        walletDbo.setId(wallet.getId());
        walletDbo.setBalance(wallet.getBalance());
        walletDbo.setUser(UserDBMapper.domainToDbo(wallet.getUser()));
        return walletDbo;
    }
}