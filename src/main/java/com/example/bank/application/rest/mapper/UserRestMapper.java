package com.example.bank.application.rest.mapper;

import com.example.bank.application.rest.dto.CreateUserDto;
import com.example.bank.domain.entity.User;
import com.example.bank.application.rest.dto.UserDto;

public class UserRestMapper {
    public static User dtoToDomain(CreateUserDto createUserDto) {
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        return user;
    }

    public static UserDto domainToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
