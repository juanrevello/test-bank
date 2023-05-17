package com.example.bank.application.usecase;

import com.example.bank.domain.entity.Transfer;

// INPUT PORT
public interface TransferWalletUseCase {
    Transfer transferWallet(Long originWalletId, Long destinationWalletId, Double amount);
}
