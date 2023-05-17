package com.example.bank.domain.repository;

import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.Wallet;

import java.util.ArrayList;

// OUTPUT PORT
public interface TransferRepository {

    Transfer save(Transfer transfer);
    ArrayList<Transfer> getTransfersByOriginWallet(Long walletId);
    ArrayList<Transfer> getTransfersByDestinationWallet(Long walletId);
}
