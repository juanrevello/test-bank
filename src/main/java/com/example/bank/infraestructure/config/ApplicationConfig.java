package com.example.bank.infraestructure.config;

import com.example.bank.domain.repository.TransferRepository;
import com.example.bank.domain.repository.WalletRepository;
import com.example.bank.domain.service.UserService;
import com.example.bank.domain.repository.UserRepository;
import com.example.bank.domain.service.WalletService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }

    @Bean
    public WalletService walletService(WalletRepository walletRepository,
                                       UserRepository userRepository,
                                       TransferRepository transferRepository) {
        return new WalletService(walletRepository, userRepository, transferRepository);
    }
}