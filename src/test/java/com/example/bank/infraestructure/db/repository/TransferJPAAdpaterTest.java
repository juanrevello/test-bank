package com.example.bank.infraestructure.db.repository;

import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.User;
import com.example.bank.domain.entity.Wallet;
import com.example.bank.infraestructure.db.dbo.TransferDbo;
import com.example.bank.infraestructure.db.dbo.UserDbo;
import com.example.bank.infraestructure.db.dbo.WalletDbo;
import com.example.bank.infraestructure.db.mapper.TransferDBMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransferJPAAdpaterTest {
    private TransferJPAAdapter transferJPAAdapter;
    @Mock
    private TransferJPARepository transferJPARepository;

    @BeforeEach
    void setup() {
        transferJPAAdapter = new TransferJPAAdapter(transferJPARepository);
    }

    @Test
    void saveOk() {
        // Given
        Wallet originWallet = new Wallet();
        originWallet.setUser(new User());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setUser(new User());

        Transfer demoTransfer = new Transfer();
        demoTransfer.setAmount(100.0);
        demoTransfer.setOriginWallet(originWallet);
        demoTransfer.setDestinationWallet(destinationWallet);

        TransferDbo demoTransferDbo = TransferDBMapper.domainToDbo(demoTransfer);

        // When
        Mockito.when(transferJPARepository.save(Mockito.any(TransferDbo.class))).thenReturn(demoTransferDbo);
        Transfer savedTransferDbo = transferJPAAdapter.save(demoTransfer);

        // Then
        Assertions.assertEquals(demoTransfer, savedTransferDbo);
    }

    @Test
    void getTransfersByOriginWalletOk() {
        // Given
        Long walletId = 1l;

        WalletDbo originWallet = new WalletDbo();
        originWallet.setUser(new UserDbo());
        originWallet.setId(walletId);

        WalletDbo destinationWallet = new WalletDbo();
        destinationWallet.setUser(new UserDbo());

        TransferDbo demoTransferDbo = new TransferDbo();
        demoTransferDbo.setAmount(100.0);
        demoTransferDbo.setOriginWallet(originWallet);
        demoTransferDbo.setDestinationWallet(destinationWallet);

        List<TransferDbo> transfersDbo = new ArrayList<>();
        transfersDbo.add(demoTransferDbo);

        // When
        Mockito.when(transferJPARepository.findByOriginWallet_Id(Mockito.any(Long.class))).thenReturn(transfersDbo);
        ArrayList<Transfer> transfers = transferJPAAdapter.getTransfersByOriginWallet(walletId);

        // Then
        Assertions.assertEquals(transfersDbo.size(), transfers.size());
    }

    @Test
    void getTransfersByDestinationWalletOk() {
        // Given
        Long walletId = 1l;

        WalletDbo originWallet = new WalletDbo();
        originWallet.setUser(new UserDbo());

        WalletDbo destinationWallet = new WalletDbo();
        destinationWallet.setUser(new UserDbo());
        originWallet.setId(walletId);

        TransferDbo demoTransferDbo = new TransferDbo();
        demoTransferDbo.setAmount(100.0);
        demoTransferDbo.setOriginWallet(originWallet);
        demoTransferDbo.setDestinationWallet(destinationWallet);

        List<TransferDbo> transfersDbo = new ArrayList<>();
        transfersDbo.add(demoTransferDbo);

        // When
        Mockito.when(transferJPARepository.findByDestinationWallet_Id(Mockito.any(Long.class))).thenReturn(transfersDbo);
        ArrayList<Transfer> transfers = transferJPAAdapter.getTransfersByDestinationWallet(walletId);

        // Then
        Assertions.assertEquals(transfersDbo.size(), transfers.size());
    }

}
