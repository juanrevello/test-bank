package com.example.bank.application.rest.controller;

import com.example.bank.application.rest.dto.CreateWalletDto;
import com.example.bank.application.rest.dto.TransferWalletDto;
import com.example.bank.application.rest.mapper.TransferRestMapper;
import com.example.bank.application.rest.mapper.WalletRestMapper;
import com.example.bank.application.usecase.*;
import com.example.bank.domain.entity.Transfer;
import com.example.bank.domain.entity.User;
import com.example.bank.domain.entity.Wallet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureJsonTesters
@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private CreateWalletUseCase createWalletUseCase;
    @MockBean
    private DepositWalletUseCase depositWalletUseCase;
    @MockBean
    private GetWalletUseCase getWalletUseCase;
    @MockBean
    private TransferWalletUseCase transferWalletUseCase;
    @MockBean
    private GetTransfersByWalletUseCase getTransfersByWalletUseCase;

    @Test
    public void createWallet_201() throws Exception {
        // Given
        CreateWalletDto createWalletDto = new CreateWalletDto();
        createWalletDto.setUserId(1l);

        Wallet createdWallet = new Wallet();

        // When
        Mockito.when(createWalletUseCase.createWallet(createWalletDto.getUserId())).thenReturn(createdWallet);

        // Then
        this.mvc.perform(post("/wallets/")
                        .content(mapper.writeValueAsString(createWalletDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(WalletRestMapper.domainToDto(createdWallet))));
    }

    @Test
    public void getWallet_200() throws Exception {
        // Given
        Long walletId = 1l;

        Wallet returnedWallet = new Wallet();
        returnedWallet.setId(walletId);
        returnedWallet.setUser(new User());

        // When
        Mockito.when(getWalletUseCase.getWallet(walletId)).thenReturn(returnedWallet);

        // Then
        this.mvc.perform(get("/wallets/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.wallet_id")).value(walletId));
    }

    @Test
    public void depositWallet_201() throws Exception {
        // Given
        TransferWalletDto transferWalletDto = new TransferWalletDto();
        transferWalletDto.setDestinationWalletId(1l);
        transferWalletDto.setAmount(100.0);

        Wallet destinationWallet = new Wallet();
        destinationWallet.setId(1l);
        destinationWallet.setBalance(0.0);

        Transfer depositedTransfer = new Transfer();
        depositedTransfer.setDestinationWallet(destinationWallet);
        depositedTransfer.setId(1l);
        depositedTransfer.setAmount(100.0);

        // When
        Mockito.when(depositWalletUseCase
                        .depositWallet(transferWalletDto.getDestinationWalletId(), transferWalletDto.getAmount()))
                .thenReturn(depositedTransfer);

        // Then
        this.mvc.perform(post("/wallets/deposit/")
                        .content(mapper.writeValueAsString(transferWalletDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(TransferRestMapper.domainToDto(depositedTransfer))));
    }

    @Test
    public void transferWallet_201() throws Exception {
        // Given
        TransferWalletDto transferWalletDto = new TransferWalletDto();
        transferWalletDto.setDestinationWalletId(1l);
        transferWalletDto.setOriginWalletId(2l);
        transferWalletDto.setAmount(100.0);

        Wallet destinationWallet = new Wallet();
        destinationWallet.setId(1l);
        destinationWallet.setBalance(0.0);

        Wallet originWallet = new Wallet();
        originWallet.setId(2l);
        originWallet.setBalance(100.0);

        Transfer depositedTransfer = new Transfer();
        depositedTransfer.setDestinationWallet(destinationWallet);
        depositedTransfer.setId(1l);
        depositedTransfer.setAmount(100.0);
        depositedTransfer.setOriginWallet(originWallet);

        // When
        Mockito.when(transferWalletUseCase.
                        transferWallet(transferWalletDto.getOriginWalletId(), transferWalletDto.getDestinationWalletId(), transferWalletDto.getAmount()))
                .thenReturn(depositedTransfer);

        // Then
        this.mvc.perform(post("/wallets/transfer/")
                        .content(mapper.writeValueAsString(transferWalletDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(TransferRestMapper.domainToDto(depositedTransfer))));
    }

}
