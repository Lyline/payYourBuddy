package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.service.ConnexionServiceImpl;
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

@WebMvcTest(ConnexionController.class)
public class ConnexionControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private ConnexionServiceImpl service;

  @Test
  void givenAUserWhenConnectThenConnexionPageDisplayed() throws Exception {
    //Given
    //When
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("connexion"))

        .andExpect(content().string(containsString("Email :")))
        .andExpect(content().string(containsString("Password :")))
        .andExpect(content().string(containsString("Connect")));
  }

  @Test
  void givenAExistUserWhenConnectThenDashboardDisplayed() throws Exception {
    //Given
    User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
    user.setUserId(1L);

    when(service.connect(any())).thenReturn(user);

    //When
    mockMvc.perform(post("/")
            .param("email","tony@test.com")
            .param("password","pw"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/1"));
  }

  @Test
  void givenANotExistUserWhenConnectThenErrorConnectDisplayed() throws Exception {
    //Given
    when(service.connect(any())).thenReturn(null);

    //When
    mockMvc.perform(post("/")
            .param("email","email@test.com")
            .param("password","password"))
        .andExpect(status().isOk())
        .andExpect(view().name("connexion"))

        .andExpect(content().string(containsString("Email or password not exist")));
  }
}
