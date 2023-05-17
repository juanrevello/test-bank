package com.example.bank.infraestructure.db.mapper;

import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.Wallet;
import com.example.bank.infraestructure.db.dbo.TransferDbo;

public class TransferDBMapper {

    public static TransferDbo domainToDbo(Transfer transfer, Wallet destinationWallet) {
        TransferDbo transferDbo = new TransferDbo();
        transferDbo.setId(transfer.getId());
        transferDbo.setAmount(transfer.getAmount());
        transferDbo.setDestinationWallet(WalletDBMapper.domainToDbo(destinationWallet));
        return transferDbo;
    }

    public static TransferDbo domainToDbo(Transfer transfer, Wallet originWallet, Wallet destinationWallet) {
        TransferDbo transferDbo = new TransferDbo();
        transferDbo.setId(transfer.getId());
        transferDbo.setAmount(transfer.getAmount());
        transferDbo.setOriginWallet(WalletDBMapper.domainToDbo(originWallet));
        transferDbo.setDestinationWallet(WalletDBMapper.domainToDbo(destinationWallet));
        return transferDbo;
    }

    public static TransferDbo domainToDbo(Transfer transfer) {
        TransferDbo transferDbo = new TransferDbo();
        transferDbo.setId(transfer.getId());
        transferDbo.setAmount(transfer.getAmount());
        transferDbo.setDestinationWallet(WalletDBMapper.domainToDbo(transfer.getDestinationWallet()));
        if (transfer.getOriginWallet() != null) {
            transferDbo.setOriginWallet(WalletDBMapper.domainToDbo(transfer.getOriginWallet()));
        }
        return transferDbo;
    }

    public static Transfer dboToDomain(TransferDbo transferDbo) {
        Transfer transfer = new Transfer();
        transfer.setId(transferDbo.getId());
        transfer.setAmount(transferDbo.getAmount());
        transfer.setDestinationWallet(WalletDBMapper.dboToDomain(transferDbo.getDestinationWallet()));
        if (transferDbo.getOriginWallet() != null) {
            transfer.setOriginWallet(WalletDBMapper.dboToDomain(transferDbo.getOriginWallet()));
        }
        return transfer;
    }

}
