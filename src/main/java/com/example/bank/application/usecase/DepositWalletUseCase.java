package com.example.bank.application.usecase;

import com.example.bank.domain.entity.Transfer;

public interface DepositWalletUseCase {
    Transfer depositWallet(Long destinationWalletId, Double amount);
}
