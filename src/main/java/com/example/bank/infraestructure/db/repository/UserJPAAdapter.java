package com.example.bank.infraestructure.db.repository;

import com.example.bank.domain.entity.Wallet;
import com.example.bank.domain.repository.UserRepository;
import com.example.bank.domain.entity.User;
import com.example.bank.infraestructure.db.dbo.UserDbo;
import com.example.bank.infraestructure.db.dbo.WalletDbo;
import com.example.bank.infraestructure.db.mapper.UserDBMapper;
import com.example.bank.infraestructure.db.mapper.WalletDBMapper;
import org.springframework.stereotype.Component;

@Component
public class UserJPAAdapter implements UserRepository {

    private final UserJPARepository userJPARepository;

    public UserJPAAdapter(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public User save(User user) {
        UserDbo userDbo = UserDBMapper.domainToDbo(user);
        UserDbo savedUserDbo = userJPARepository.save(userDbo);
        return UserDBMapper.dboToDomain(savedUserDbo);
    }

    @Override
    public User get(Long userId) {
        UserDbo userDbo = userJPARepository.findById(userId).orElse(null);
        return UserDBMapper.dboToDomain(userDbo);
    }
}
