package com.example.bank.infraestructure.db.repository;

import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.Wallet;
import com.example.bank.domain.repository.WalletRepository;
import com.example.bank.infraestructure.db.dbo.WalletDbo;
import com.example.bank.infraestructure.db.mapper.WalletDBMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

// PERSISTENCE ADAPTER

@Component
public class WalletJPAAdapter implements WalletRepository {
    private final WalletJPARepository walletJPARepository;

    public WalletJPAAdapter(WalletJPARepository walletJPARepository) {
        this.walletJPARepository = walletJPARepository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletDbo walletDbo = WalletDBMapper.domainToDbo(wallet);
        WalletDbo savedWalletDbo = walletJPARepository.save(walletDbo);
        return WalletDBMapper.dboToDomain(savedWalletDbo);
    }

    @Override
    public Wallet get(Long walletId) {
        WalletDbo walletDbo = walletJPARepository.findById(walletId).orElse(null);
        return WalletDBMapper.dboToDomain(walletDbo);
    }

    @Override
    public Wallet update(Wallet wallet) {
        WalletDbo walletDbo = walletJPARepository.findById(wallet.getId()).orElse(null);
        walletDbo.setBalance(wallet.getBalance());
        WalletDbo savedWalletDbo = walletJPARepository.save(walletDbo);
        return WalletDBMapper.dboToDomain(savedWalletDbo);
    }

}
