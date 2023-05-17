package com.example.bank.application.rest.controller;

import com.example.bank.application.rest.dto.CreateUserDto;
import com.example.bank.application.rest.dto.WalletDto;
import com.example.bank.application.rest.mapper.WalletRestMapper;
import com.example.bank.application.usecase.CreateUserUseCase;
import com.example.bank.application.usecase.GetUserUseCase;
import com.example.bank.application.usecase.GetWalletUseCase;
import com.example.bank.domain.entity.User;
import com.example.bank.application.rest.dto.UserDto;
import com.example.bank.application.rest.mapper.UserRestMapper;
import com.example.bank.domain.entity.Wallet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// REST ADAPTER

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    private final GetUserUseCase getUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
                          GetUserUseCase getUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto){
          User createdUser = createUserUseCase.createUser(UserRestMapper. dtoToDomain(createUserDto));
          return new ResponseEntity<>(UserRestMapper.domainToDto(createdUser), HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("user_id") Long userId){
        User getUser = getUserUseCase.getUser(userId);
        return new ResponseEntity<>(UserRestMapper.domainToDto(getUser), HttpStatus.OK);
    }
}
