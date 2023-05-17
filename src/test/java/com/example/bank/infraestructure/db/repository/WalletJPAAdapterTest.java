package com.example.bank.infraestructure.db.repository;

import com.example.bank.domain.entity.User;
import com.example.bank.domain.entity.Wallet;
import com.example.bank.infraestructure.db.dbo.UserDbo;
import com.example.bank.infraestructure.db.dbo.WalletDbo;
import com.example.bank.infraestructure.db.mapper.WalletDBMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class WalletJPAAdapterTest {
    private WalletJPAAdapter walletJPAAdapter;
    @Mock
    private WalletJPARepository walletJPARepository;

    @BeforeEach
    void setup() {
        walletJPAAdapter = new WalletJPAAdapter(walletJPARepository);
    }

    @Test
    void saveOk() {
        // Given
        User demoUser = new User();
        demoUser.setId(1l);
        demoUser.setEmail("j@j.com");
        demoUser.setPassword("password");

        Wallet demoWallet = new Wallet();
        demoWallet.setUser(demoUser);

        WalletDbo demoWalletDbo = WalletDBMapper.domainToDbo(demoWallet);

        // When
        Mockito.when(walletJPARepository.save(Mockito.any(WalletDbo.class))).thenReturn(demoWalletDbo);
        Wallet wallet = walletJPAAdapter.save(demoWallet);

        // Then
        Assertions.assertEquals(demoWallet, wallet);
    }

    @Test
    void getOk() {
        // Given
        Long walletId = 1l;

        WalletDbo demoWalletDbo = new WalletDbo();
        demoWalletDbo.setId(walletId);
        demoWalletDbo.setUser(new UserDbo());

        // When
        Mockito.when(walletJPARepository.findById(walletId)).thenReturn(Optional.of(demoWalletDbo));
        Wallet wallet = walletJPAAdapter.get(walletId);

        // Then
        Assertions.assertEquals(walletId, wallet.getId());
    }

    @Test
    void updateOk() {
        // Given
        User demoUser = new User();
        demoUser.setId(1l);
        demoUser.setEmail("j@j.com");
        demoUser.setPassword("password");

        Wallet demoWallet = new Wallet();
        demoWallet.setUser(demoUser);
        demoWallet.setId(1l);
        demoWallet.setBalance(100.0);

        WalletDbo originalWalletDbo = WalletDBMapper.domainToDbo(demoWallet);
        originalWalletDbo.setBalance(0.0);

        WalletDbo modifiedWalletDbo = WalletDBMapper.domainToDbo(demoWallet);

        // When
        Mockito.when(walletJPARepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(originalWalletDbo));
        Mockito.when(walletJPARepository.save(Mockito.any(WalletDbo.class))).thenReturn(modifiedWalletDbo);
        Wallet wallet = walletJPAAdapter.update(demoWallet);

        // Then
        Assertions.assertEquals(modifiedWalletDbo.getBalance(), wallet.getBalance());
    }
}
