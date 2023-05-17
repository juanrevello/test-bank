package com.example.bank.application.usecase;

import com.example.bank.domain.entity.Wallet;

// INPUT PORT
public interface GetWalletUseCase {
    Wallet getWallet(Long walletId);
}
