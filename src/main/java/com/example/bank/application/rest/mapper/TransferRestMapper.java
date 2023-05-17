package com.example.bank.application.rest.mapper;

import com.example.bank.application.rest.dto.TransferDto;
import com.example.bank.domain.entity.Transfer;

public class TransferRestMapper {
    public static TransferDto domainToDto(Transfer transfer){
        TransferDto transferDto = new TransferDto();
        transferDto.setId(transfer.getId());
        transferDto.setAmount(transfer.getAmount());
        transferDto.setDestinationWallet(WalletRestMapper.domainToDto(transfer.getDestinationWallet()));
        transferDto.getDestinationWallet().setUser(null);
        if (transfer.getOriginWallet() != null) {
            transferDto.setOriginWallet(WalletRestMapper.domainToDto(transfer.getOriginWallet()));
            transferDto.getOriginWallet().setUser(null);
        }
        return transferDto;
    }
}
