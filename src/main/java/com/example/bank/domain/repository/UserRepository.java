package com.example.bank.domain.repository;

import com.example.bank.domain.entity.User;

public interface UserRepository {
    User save(User user);

    User get(Long userId);
}
