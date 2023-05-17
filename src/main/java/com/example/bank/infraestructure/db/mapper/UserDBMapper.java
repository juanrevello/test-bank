package com.example.bank.infraestructure.db.mapper;

import com.example.bank.domain.entity.User;
import com.example.bank.infraestructure.db.dbo.UserDbo;

public class UserDBMapper {

    public static User dboToDomain(UserDbo userDbo){
        User user = new User();
        user.setId(userDbo.getId());
        user.setEmail(userDbo.getEmail());
        user.setPassword(userDbo.getPassword());
        return user;
    }

    public static UserDbo domainToDbo(User user){
        UserDbo userDbo = new UserDbo();
        userDbo.setId(user.getId());
        userDbo.setEmail(user.getEmail());
        userDbo.setPassword(user.getPassword());
        return userDbo;
    }
}
