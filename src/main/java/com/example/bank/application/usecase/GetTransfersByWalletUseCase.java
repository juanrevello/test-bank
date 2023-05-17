package com.example.bank.application.usecase;

import com.example.bank.domain.entity.Transfer;

import java.util.ArrayList;

public interface GetTransfersByWalletUseCase {
    ArrayList<Transfer> getTransfers(Long walletId);
}
