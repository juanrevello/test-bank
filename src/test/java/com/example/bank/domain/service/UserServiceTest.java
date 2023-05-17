package com.example.bank.domain.service;

import com.example.bank.domain.entity.User;
import com.example.bank.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUserOk() {
        // Given
        User demoUser = new User();
        demoUser.setId(1l);
        demoUser.setEmail("juan@gmail.com");
        demoUser.setPassword("password");

        // When
        Mockito.when(userRepository.get(demoUser.getId())).thenReturn(demoUser);
        User user = userService.getUser(demoUser.getId());

        // Then
        Assertions.assertEquals(demoUser.getId(), user.getId());
        Assertions.assertEquals(demoUser.getEmail(), user.getEmail());
        Assertions.assertEquals(demoUser.getPassword(), user.getPassword());
    }

    @Test
    public void createUserOk() {
        // Given
        User demoUser = new User();
        demoUser.setEmail("juan@gmail.com");
        demoUser.setPassword("password");

        // When
        Mockito.when(userRepository.save(demoUser)).thenReturn(demoUser);
        User user = userService.createUser(demoUser);

        // Then
        Assertions.assertEquals(demoUser.getEmail(), user.getEmail());
        Assertions.assertEquals(demoUser.getPassword(), user.getPassword());
    }
}
