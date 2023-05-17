package com.example.bank.application.rest.controller;

import com.example.bank.application.rest.dto.CreateUserDto;
import com.example.bank.application.rest.mapper.UserRestMapper;
import com.example.bank.application.usecase.CreateUserUseCase;
import com.example.bank.application.usecase.GetUserUseCase;
import com.example.bank.domain.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();
    @MockBean
    CreateUserUseCase createUserUseCase;
    @MockBean
    GetUserUseCase getUserUseCase;

    @Test
    public void createUser_201() throws Exception {
        // Given
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setEmail("email");
        createUserDto.setPassword("password");

        User createdUser = new User();
        createdUser.setEmail("email");
        createdUser.setPassword("password");
        createdUser.setId(1l);

        // When
        Mockito.when(createUserUseCase.createUser(Mockito.any(User.class))).thenReturn(createdUser);

        // Then
        this.mvc.perform(post("/users/")
                        .content(mapper.writeValueAsString(createUserDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(UserRestMapper.domainToDto(createdUser))));
    }

    @Test
    public void getUser_200() throws Exception {
        // Given
        Long userId = 1l;

        User responseUser = new User();
        responseUser.setId(1l);

        // When
        Mockito.when(getUserUseCase.getUser(userId)).thenReturn(responseUser);

        // Then
        this.mvc.perform(get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.user_id")).value(userId));
    }
}
