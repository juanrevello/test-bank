package com.example.bank.infraestructure.db.repository;

import com.example.bank.domain.entity.User;
import com.example.bank.infraestructure.db.dbo.UserDbo;
import com.example.bank.infraestructure.db.mapper.UserDBMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserJPAAdapterTest {
    private UserJPAAdapter userJPAAdapter;
    @Mock
    private UserJPARepository userJPARepository;

    @BeforeEach
    void setup() {
        userJPAAdapter = new UserJPAAdapter(userJPARepository);
    }

    @Test
    void getOk() {
        // Given
        Long userId = 1l;

        UserDbo demoUserDbo = new UserDbo();
        demoUserDbo.setId(userId);

        // When
        Mockito.when(userJPARepository.findById(userId)).thenReturn(Optional.of(demoUserDbo));
        User user = userJPAAdapter.get(userId);

        // Then
        Assertions.assertEquals(userId, user.getId());
    }

    @Test
    void saveOk() {
        // Given
        User demoUser = new User();
        demoUser.setId(1l);
        demoUser.setEmail("j@j.com");
        demoUser.setPassword("password");

        UserDbo demoUserDbo = UserDBMapper.domainToDbo(demoUser);

        // When
        Mockito.when(userJPARepository.save(Mockito.any(UserDbo.class))).thenReturn(demoUserDbo);
        User user = userJPAAdapter.save(demoUser);

        // Then
        Assertions.assertEquals(demoUser, user);
    }
}
