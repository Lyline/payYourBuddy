package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl userServiceImpl;

  @Test
  void homeControllerWithTransactionsTest() throws Exception {
    User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
    User user1=new User("Steve","Rogers","steve@test.com","pw","bank",100.);

    UserTransaction userTransaction=new UserTransaction(1L,user,user1,"new transaction3",30.);
    BankTransaction bankTransaction=new BankTransaction(1L,user,"bank transaction1",-30.);

    user.getUserTransactions().add(userTransaction);
    user.getBankTransactions().add(bankTransaction);
    user.getFriends().add(user1);

    when(userServiceImpl.getUser(1L)).thenReturn(java.util.Optional.of(user));

    mockMvc.perform(get("/1"))
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

  @Test
  void homeControllerWithoutTransactionTest() throws Exception {
    User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);

    when(userServiceImpl.getUser(1L)).thenReturn(java.util.Optional.of(user));

    mockMvc.perform(get("/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("home"))

        .andExpect(content().string(containsString("Hi, <span>Tony</span>")))

        .andExpect(content().string(containsString("Actual credit :")))
        .andExpect(content().string(containsString("100.")))

        .andExpect(content().string(containsString("Last transaction :")))
        .andExpect(content().string(containsString("No user transaction yet")))

        .andExpect(content().string(containsString("Last transfert :")))
        .andExpect(content().string(containsString("No bank transaction yet")));
  }
}