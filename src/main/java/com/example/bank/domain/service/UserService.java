package com.example.bank.domain.service;

import com.example.bank.application.usecase.CreateUserUseCase;
import com.example.bank.application.usecase.GetUserUseCase;
import com.example.bank.domain.entity.User;
import com.example.bank.domain.repository.UserRepository;

// DOMAIN SERVICE

public class UserService implements CreateUserUseCase, GetUserUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.get(userId);
    }
}
