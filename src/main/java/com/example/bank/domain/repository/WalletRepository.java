package com.example.bank.domain.repository;

import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.User;
import com.example.bank.domain.entity.Wallet;

import java.util.ArrayList;

// OUTPUT PORT

public interface WalletRepository {
    Wallet save (Wallet wallet);

    Wallet get(Long walletId);

    Wallet update(Wallet wallet);
}
