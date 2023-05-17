package com.example.bank.application.rest.mapper;

import com.example.bank.application.rest.dto.WalletDto;
import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.Wallet;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class WalletRestMapper {
    public static WalletDto domainToDto(Wallet wallet) {
        WalletDto walletDto = new WalletDto();
        walletDto.setId(wallet.getId());
        walletDto.setBalance(wallet.getBalance());
        if (wallet.getUser() != null) {
            walletDto.setUser(UserRestMapper.domainToDto(wallet.getUser()));
        }
        return walletDto;
    }

    public static WalletDto domainToDto(Wallet wallet, ArrayList<Transfer> transfers) {
        WalletDto walletDto = new WalletDto();
        walletDto.setId(wallet.getId());
        walletDto.setBalance(wallet.getBalance());
        if (wallet.getUser() != null) {
            walletDto.setUser(UserRestMapper.domainToDto(wallet.getUser()));
        }
        walletDto.setTransfers(transfers
                .stream()
                .map(x -> TransferRestMapper.domainToDto(x))
                .collect(Collectors.toCollection(ArrayList::new)));
        return walletDto;
    }
}
