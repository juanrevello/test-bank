package com.example.bank.domain.service;

import com.example.bank.application.usecase.*;
import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.User;
import com.example.bank.domain.entity.Wallet;
import com.example.bank.domain.repository.TransferRepository;
import com.example.bank.domain.repository.UserRepository;
import com.example.bank.domain.repository.WalletRepository;

import java.util.ArrayList;

public class WalletService implements CreateWalletUseCase,
        GetWalletUseCase, DepositWalletUseCase,
        TransferWalletUseCase, GetTransfersByWalletUseCase {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;

    public WalletService(WalletRepository walletRepository,
                         UserRepository userRepository,
                         TransferRepository transferRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.transferRepository = transferRepository;
    }

    @Override
    public Wallet createWallet(Long userId) {
        User user = userRepository.get(userId);
        if (user != null) {
            Wallet wallet = new Wallet();
            wallet.setBalance(0d);
            wallet.setUser(user);
            return walletRepository.save(wallet);
        }
        return null;
    }

    @Override
    public Wallet getWallet(Long walletId) {
        return walletRepository.get(walletId);
    }

    @Override
    public ArrayList<Transfer> getTransfers(Long walletId) {
        ArrayList<Transfer> transfers = new ArrayList<>();
        transfers.addAll(transferRepository.getTransfersByOriginWallet(walletId));
        transfers.addAll(transferRepository.getTransfersByDestinationWallet(walletId));
        return transfers;
    }

    @Override
    public Transfer depositWallet(Long destinationWalletId, Double amount) {
        if (amount > 0) {
            Wallet destinationWallet = walletRepository.get(destinationWalletId);
            destinationWallet.deposit(amount);
            Transfer transfer = new Transfer();
            transfer.setAmount(amount);
            transfer.setDestinationWallet(destinationWallet);

            Transfer savedTransfer = transferRepository.save(transfer);
            walletRepository.update(destinationWallet);
            return savedTransfer;
        }
        return null;
    }

    @Override
    public Transfer transferWallet(Long originWalletId, Long destinationWalletId, Double amount) {
        if (amount > 0) {
            Wallet originWallet = walletRepository.get(originWalletId);
            Wallet destinationWallet = walletRepository.get(destinationWalletId);
            if (originWallet.getBalance() >= amount) {
                destinationWallet.deposit(amount);
                originWallet.withdrawal(amount);

                Transfer transfer = new Transfer();
                transfer.setAmount(amount);
                transfer.setOriginWallet(originWallet);
                transfer.setDestinationWallet(destinationWallet);

                Transfer savedTransfer = transferRepository.save(transfer);

                walletRepository.update(originWallet);
                walletRepository.update(destinationWallet);
                return savedTransfer;
            }
        }
        return null;
    }

}
