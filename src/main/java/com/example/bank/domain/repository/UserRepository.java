package com.example.bank.domain.repository;

import com.example.bank.domain.entity.User;

// OUTPUT PORT

//podrian pasarse tipos <T>
public interface UserRepository {
    User save(User user);
    User get(Long userId);
}
