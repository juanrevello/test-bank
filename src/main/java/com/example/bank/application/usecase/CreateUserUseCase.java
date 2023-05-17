package com.example.bank.application.usecase;

import com.example.bank.domain.entity.User;

// INPUT PORT

public interface CreateUserUseCase {
    public User createUser(User user);
}
