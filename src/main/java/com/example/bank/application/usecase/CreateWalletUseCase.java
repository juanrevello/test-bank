package com.example.bank.application.usecase;

import com.example.bank.domain.entity.Wallet;

public interface CreateWalletUseCase {
    Wallet createWallet(Long userId);
}
