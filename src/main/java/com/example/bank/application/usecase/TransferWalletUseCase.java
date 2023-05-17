package com.example.bank.application.usecase;

import com.example.bank.domain.entity.Transfer;

public interface TransferWalletUseCase {
    Transfer transferWallet(Long originWalletId, Long destinationWalletId, Double amount);
}
