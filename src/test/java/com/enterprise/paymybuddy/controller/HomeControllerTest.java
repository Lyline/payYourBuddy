package com.enterprise.paymybuddy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void homeControllerWithTransactionsTest() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("home"))

        .andExpect(content().string(containsString("Hi, <span>Tony</span>")))

        .andExpect(content().string(containsString("Actual credit :")))
        .andExpect(content().string(containsString("100.")))

        .andExpect(content().string(containsString("Last transaction :")))
        .andExpect(content().string(containsString("Steve Rogers")))
        .andExpect(content().string(containsString("new transaction3")))
        .andExpect(content().string(containsString("-30.0")))

        .andExpect(content().string(containsString("Last transfert :")))
        .andExpect(content().string(containsString("bank transaction1")))
        .andExpect(content().string(containsString("-30.0")));
  }
}