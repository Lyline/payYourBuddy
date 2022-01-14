package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl service;

  @Test
  void givenAPersonWhenCreateAccountThenAccountPageDisplayed() throws Exception {
    mockMvc.perform(get("/register"))
        .andExpect(status().isOk())
        .andExpect(view().name("/register"))

        .andExpect(content().string(containsString("First name :")))
        .andExpect(content().string(containsString("Last name :")))
        .andExpect(content().string(containsString("Email :")))
        .andExpect(content().string(containsString("Password :")))
        .andExpect(content().string(containsString("Bank account :")));
  }

  @Test
  void givenAPersonWhenCreateAccountWithValidateInformationsThenUserCreated() throws Exception {
    //Given
    when(service.createUser(any())).thenReturn(true);

    //When
    mockMvc.perform(post("/register")
        .param("firstName","Tony")
        .param("lastName","Stark")
        .param("email","test@email.com")
        .param("password","pw")
        .param("bankAccount","bank"))

        .andExpect(status().isOk())
        .andExpect(view().name("/register"))

        .andExpect(content().string(containsString("You are created in the matrix !")));
  }

  @Test
  void givenAPersonWhenCreateAccountWithAExistEmailThenUserNotCreated() throws Exception {
    //Given
    when(service.createUser(any())).thenReturn(false);

    //When
    mockMvc.perform(post("/register")
            .param("firstName","Tony")
            .param("lastName","Stark")
            .param("email","test@email.com")
            .param("password","pw")
            .param("bankAccount","bank"))

        .andExpect(status().isOk())
        .andExpect(view().name("/register"))

        .andExpect(content().string(containsString("Sorry, this email already exist")));
  }
}
