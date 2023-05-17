package com.example.bank.infraestructure.db.repository;

import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.repository.TransferRepository;
import com.example.bank.infraestructure.db.dbo.TransferDbo;
import com.example.bank.infraestructure.db.mapper.TransferDBMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransferJPAAdapter implements TransferRepository {
    private final TransferJPARepository transferJPARepository;

    public TransferJPAAdapter(TransferJPARepository transferJPARepository) {
        this.transferJPARepository = transferJPARepository;
    }

    @Override
    public Transfer save(Transfer transfer) {
        TransferDbo transferDbo = TransferDBMapper.domainToDbo(transfer);
        TransferDbo savedTransferDbo = transferJPARepository.save(transferDbo);
        return TransferDBMapper.dboToDomain(savedTransferDbo);
    }

    @Override
    public ArrayList<Transfer> getTransfersByOriginWallet(Long walletId) {
        List<TransferDbo> transfersDbo = transferJPARepository.findByOriginWallet_Id(walletId);
        ArrayList<Transfer> transfers = new ArrayList<>();
        transfers = transfersDbo
                .stream()
                .map(x -> TransferDBMapper.dboToDomain(x))
                .collect(Collectors.toCollection(ArrayList::new));
        return transfers;
    }

    @Override
    public ArrayList<Transfer> getTransfersByDestinationWallet(Long walletId) {
        List<TransferDbo> transfersDbo = transferJPARepository.findByDestinationWallet_Id(walletId);
        ArrayList<Transfer> transfers = new ArrayList<>();
        transfers = transfersDbo
                .stream()
                .map(x -> TransferDBMapper.dboToDomain(x))
                .collect(Collectors.toCollection(ArrayList::new));
        return transfers;
    }

}
