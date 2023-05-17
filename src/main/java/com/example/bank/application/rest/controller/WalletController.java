package com.example.bank.application.rest.controller;

import com.example.bank.application.rest.dto.CreateWalletDto;
import com.example.bank.application.rest.dto.TransferWalletDto;
import com.example.bank.application.rest.dto.TransferDto;
import com.example.bank.application.rest.dto.WalletDto;
import com.example.bank.application.rest.mapper.TransferRestMapper;
import com.example.bank.application.rest.mapper.WalletRestMapper;
import com.example.bank.application.usecase.*;
import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.Wallet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// REST ADAPTER

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final CreateWalletUseCase createWalletUseCase;
    private final DepositWalletUseCase depositWalletUseCase;
    private final GetWalletUseCase getWalletUseCase;
    private final TransferWalletUseCase transferWalletUseCase;
    private final GetTransfersByWalletUseCase getTransfersByWalletUseCase;

    public WalletController(CreateWalletUseCase createWalletUseCase,
                            GetWalletUseCase getWalletUseCase,
                            DepositWalletUseCase depositWalletUseCase,
                            TransferWalletUseCase transferWalletUseCase,
                            GetTransfersByWalletUseCase getTransfersByWalletUseCase) {

        this.createWalletUseCase = createWalletUseCase;
        this.depositWalletUseCase = depositWalletUseCase;
        this.getWalletUseCase = getWalletUseCase;
        this.transferWalletUseCase = transferWalletUseCase;
        this.getTransfersByWalletUseCase = getTransfersByWalletUseCase;
    }

    @PostMapping
    public ResponseEntity<WalletDto> createWallet(@RequestBody CreateWalletDto createWalletDto){
        Wallet createdWallet = createWalletUseCase.createWallet(createWalletDto.getUserId());
        return new ResponseEntity<>(WalletRestMapper.domainToDto(createdWallet),HttpStatus.CREATED);
    }

    @GetMapping("/{wallet_id}")
    public ResponseEntity<WalletDto> getWallet(@PathVariable("wallet_id") Long walletId){
        Wallet getWallet = getWalletUseCase.getWallet(walletId);
        ArrayList<Transfer> transfers = getTransfersByWalletUseCase.getTransfers(walletId);
        return new ResponseEntity<>(WalletRestMapper.domainToDto(getWallet, transfers), HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransferDto> depositWallet(@RequestBody TransferWalletDto transferWalletDto){
        Transfer depositedTransfer = depositWalletUseCase.depositWallet(transferWalletDto.getDestinationWalletId(), transferWalletDto.getAmount());
        return new ResponseEntity<>(TransferRestMapper.domainToDto(depositedTransfer),HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferDto> transferWallet(@RequestBody TransferWalletDto transferWalletDto){
        Transfer transfer = transferWalletUseCase.transferWallet(transferWalletDto.getOriginWalletId(), transferWalletDto.getDestinationWalletId(), transferWalletDto.getAmount());
        return new ResponseEntity<>(TransferRestMapper.domainToDto(transfer),HttpStatus.CREATED);
    }

}
