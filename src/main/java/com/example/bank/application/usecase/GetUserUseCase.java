package com.example.bank.application.usecase;

import com.example.bank.domain.entity.User;

public interface GetUserUseCase {
    User getUser(Long userId);
}
