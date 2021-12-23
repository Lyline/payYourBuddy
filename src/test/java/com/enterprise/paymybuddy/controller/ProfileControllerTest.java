package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl service;

  User user=new User();

  @Test
  void givenAnExistingUserWhenShowProfileThenHisProfileDisplayed() throws Exception {
    //Given
    user.setUserId(1L);
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setEmail("john@email.com");
    user.setBalance(100.);
    user.setBankAccount("myBank");

    when(service.getUser(1L)).thenReturn(java.util.Optional.ofNullable(user));

    //When
    mockMvc.perform(get("/1/profile"))
        .andExpect(status().isOk())
        .andExpect(view().name("profile"))

        .andExpect(content().string(containsString("First name :")))
        .andExpect(content().string(containsString("John")))

        .andExpect(content().string(containsString("Last name :")))
        .andExpect(content().string(containsString("Doe")))

        .andExpect(content().string(containsString("Email :")))
        .andExpect(content().string(containsString("john@email.com")))

        .andExpect(content().string(containsString("Balance :")))
        .andExpect(content().string(containsString("100.")))

        .andExpect(content().string(containsString("Bank account number :")))
        .andExpect(content().string(containsString("myBank")));

    verify(service,times(1)).getUser(1L);
  }
}