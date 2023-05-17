package com.example.bank.domain.repository;

import com.example.bank.domain.entity.Wallet;

public interface WalletRepository {
    Wallet save(Wallet wallet);

    Wallet get(Long walletId);

    Wallet update(Wallet wallet);
}
