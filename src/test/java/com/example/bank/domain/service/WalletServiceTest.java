package com.example.bank.domain.service;

import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.User;
import com.example.bank.domain.entity.Wallet;
import com.example.bank.domain.repository.TransferRepository;
import com.example.bank.domain.repository.UserRepository;
import com.example.bank.domain.repository.WalletRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class WalletServiceTest {

    private WalletService walletService;
    private WalletRepository walletRepository;
    private UserRepository userRepository;
    private TransferRepository transferRepository;

    @BeforeEach
    void setUp() {
        walletRepository = Mockito.mock(WalletRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        transferRepository = Mockito.mock(TransferRepository.class);
        walletService = new WalletService(
                walletRepository,
                userRepository,
                transferRepository
        );
    }

    @Test
    void createWalletOk() {
        // Given
        User demoUser = new User();
        demoUser.setId(1l);

        Wallet demoWallet = new Wallet();
        demoWallet.setBalance(0.0);
        demoWallet.setUser(demoUser);

        // When
        Mockito.when(userRepository.get(demoUser.getId())).thenReturn(demoUser);
        Mockito.when(walletRepository.save(demoWallet)).thenReturn(demoWallet);

        Wallet wallet = walletService.createWallet(demoUser.getId());

        // Then
        Assertions.assertEquals(demoWallet, wallet);
        Mockito.verify(userRepository, Mockito.times(1)).get(Mockito.any(Long.class));
        Mockito.verify(walletRepository, Mockito.times(1)).save(Mockito.any(Wallet.class));
    }

    @Test
    void createWalletUserReturnNull() {
        // Given
        User demoUser = new User();
        demoUser.setId(1l);

        Wallet demoWallet = new Wallet();
        demoWallet.setBalance(0.0);
        demoWallet.setUser(demoUser);

        // When
        Mockito.when(userRepository.get(demoUser.getId())).thenReturn(null);
        Wallet wallet = walletService.createWallet(demoUser.getId());

        // Then
        Assertions.assertNull(wallet);
        Mockito.verify(userRepository, Mockito.times(1)).get(Mockito.any(Long.class));
        Mockito.verify(walletRepository, Mockito.times(0)).save(Mockito.any(Wallet.class));
    }

    @Test
    void getWalletOk() {
        // Given
        Wallet demoWallet = new Wallet();
        demoWallet.setId(1L);

        // When
        Mockito.when(walletRepository.get(demoWallet.getId())).thenReturn(demoWallet);
        Wallet wallet = walletService.getWallet(demoWallet.getId());

        // Then
        Assertions.assertEquals(demoWallet, wallet);
    }

    @Test
    void getTransfersOk() {
        // Given
        Wallet demoWallet = new Wallet();
        demoWallet.setId(1l);

        Transfer inputTransfer = new Transfer();
        inputTransfer.setId(1l);
        inputTransfer.setAmount(10.0);
        inputTransfer.setDestinationWallet(demoWallet);

        Transfer outputTransfer = new Transfer();
        outputTransfer.setId(2l);
        outputTransfer.setAmount(1.0);
        outputTransfer.setOriginWallet(demoWallet);

        ArrayList<Transfer> inputTransfers = new ArrayList<>();
        inputTransfers.add(inputTransfer);

        ArrayList<Transfer> outputTransfers = new ArrayList<>();
        outputTransfers.add(outputTransfer);

        ArrayList<Transfer> expectedTransfers = new ArrayList<>();
        expectedTransfers.add(inputTransfer);
        expectedTransfers.add(outputTransfer);

        // When
        Mockito.when(transferRepository.getTransfersByOriginWallet(demoWallet.getId())).thenReturn(outputTransfers);
        Mockito.when(transferRepository.getTransfersByDestinationWallet(demoWallet.getId())).thenReturn(inputTransfers);
        ArrayList<Transfer> transfers = walletService.getTransfers(demoWallet.getId());

        // Then
        Assertions.assertEquals(expectedTransfers.size(), transfers.size());
    }

    @Test
    void depositWalletReturnNull() {
        // Given
        Long destinationWalletId = 1l;
        Double amount = 0.0;

        // When
        Transfer transfer = walletService.depositWallet(destinationWalletId, amount);

        // Then
        Assertions.assertNull(transfer);
        Mockito.verify(walletRepository, Mockito.times(0)).get(Mockito.any(Long.class));
        Mockito.verify(transferRepository, Mockito.times(0)).save(Mockito.any(Transfer.class));
        Mockito.verify(walletRepository, Mockito.times(0)).update(Mockito.any(Wallet.class));
    }

    @Test
    void depositWalletReturnOk() {
        // Given
        Long destinationWalletId = 1l;
        Double amount = 100.0;

        Wallet destinationWallet = new Wallet();
        destinationWallet.setId(1l);
        destinationWallet.setBalance(10.0);

        Transfer demoTransfer = new Transfer();
        demoTransfer.setAmount(100.0);
        demoTransfer.setDestinationWallet(destinationWallet);

        Wallet modifiedWallet = new Wallet();
        modifiedWallet.setId(1l);
        modifiedWallet.setBalance(110.0);

        Transfer completedTransfer = new Transfer();
        completedTransfer.setDestinationWallet(modifiedWallet);
        completedTransfer.setAmount(100.0);

        // When
        Mockito.when(walletRepository.get(destinationWalletId)).thenReturn(destinationWallet);
        Mockito.when(transferRepository.save(demoTransfer)).thenReturn(completedTransfer);
        Transfer transfer = walletService.depositWallet(destinationWalletId, amount);

        // Then
        Assertions.assertEquals(completedTransfer, transfer);
        Mockito.verify(walletRepository, Mockito.times(1)).get(Mockito.any(Long.class));
        Mockito.verify(transferRepository, Mockito.times(1)).save(Mockito.any(Transfer.class));
        Mockito.verify(walletRepository, Mockito.times(1)).update(Mockito.any(Wallet.class));
    }

    @Test
    void transferWalletReturnNull() {
        // Given
        Long originWalletId = 1l;
        Long destinationWalletId = 2l;
        Double amount = 100.0;

        Wallet originWallet = new Wallet();
        originWallet.setId(originWalletId);
        originWallet.setBalance(90.0);

        Wallet destinationWallet = new Wallet();
        destinationWallet.setId(destinationWalletId);
        destinationWallet.setBalance(0.0);

        // When
        Mockito.when(walletRepository.get(originWalletId)).thenReturn(originWallet);
        Mockito.when(walletRepository.get(destinationWalletId)).thenReturn(destinationWallet);
        Transfer transfer = walletService.transferWallet(originWalletId, destinationWalletId, amount);

        // Then
        Assertions.assertNull(transfer);
        Mockito.verify(walletRepository, Mockito.times(2)).get(Mockito.any(Long.class));
        Mockito.verify(transferRepository, Mockito.times(0)).save(Mockito.any(Transfer.class));
        Mockito.verify(walletRepository, Mockito.times(0)).update(Mockito.any(Wallet.class));
    }

    @Test
    void transferWalletReturnOk() {
        // Given
        Long originWalletId = 1l;
        Long destinationWalletId = 2l;
        Double amount = 10.0;

        Wallet originWallet = new Wallet();
        originWallet.setId(originWalletId);
        originWallet.setBalance(90.0);

        Wallet destinationWallet = new Wallet();
        destinationWallet.setId(destinationWalletId);
        destinationWallet.setBalance(0.0);

        Wallet modifiedOriginWallet = new Wallet();
        modifiedOriginWallet.setId(originWalletId);
        modifiedOriginWallet.setBalance(80.0);

        Wallet modifiedDestinationWallet = new Wallet();
        modifiedDestinationWallet.setId(destinationWalletId);
        modifiedDestinationWallet.setBalance(10.0);

        Transfer demoTransfer = new Transfer();
        demoTransfer.setAmount(10.0);
        demoTransfer.setOriginWallet(originWallet);
        demoTransfer.setDestinationWallet(destinationWallet);

        Transfer completedTransfer = new Transfer();
        completedTransfer.setAmount(10.0);
        completedTransfer.setOriginWallet(modifiedOriginWallet);
        completedTransfer.setDestinationWallet(modifiedDestinationWallet);

        // When
        Mockito.when(walletRepository.get(originWalletId)).thenReturn(originWallet);
        Mockito.when(walletRepository.get(destinationWalletId)).thenReturn(destinationWallet);
        Mockito.when(transferRepository.save(demoTransfer)).thenReturn(completedTransfer);
        Transfer transfer = walletService.transferWallet(originWalletId, destinationWalletId, amount);

        // Then
        Assertions.assertEquals(completedTransfer, transfer);
        Mockito.verify(walletRepository, Mockito.times(2)).get(Mockito.any(Long.class));
        Mockito.verify(transferRepository, Mockito.times(1)).save(Mockito.any(Transfer.class));
        Mockito.verify(walletRepository, Mockito.times(2)).update(Mockito.any(Wallet.class));
    }

}
