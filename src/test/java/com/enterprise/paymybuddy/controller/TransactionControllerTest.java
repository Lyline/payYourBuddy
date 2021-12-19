package com.enterprise.paymybuddy.controller;

import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.service.BankTransactionServiceImpl;
import com.enterprise.paymybuddy.service.UserServiceImpl;
import com.enterprise.paymybuddy.service.UserTransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl userService;

  @MockBean
  private UserTransactionServiceImpl userTransactionService;

  @MockBean
  private BankTransactionServiceImpl bankTransactionService;


  User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
  User user1=new User("Steve","Rogers","steve@test.com","pw","bank",100.);

  List<UserTransaction> userTransactions=new ArrayList<>();
  UserTransaction userTransaction=new UserTransaction(1L,user,user1,"New user transaction",30.);
  UserTransaction userTransaction1=new UserTransaction(2L,user1,user,"One more new user transaction",30.);

  List<BankTransaction> bankTransactions=new ArrayList<>();
  BankTransaction bankTransaction=new BankTransaction(1L,user,"New bank transaction",-130.);
  BankTransaction bankTransaction1=new BankTransaction(2L,user,"One more new bank transaction",230.);


  @Test
  void givenAUserWithTwoTransactionsWhenShowUserTransactionsThenTwoTransactionsDisplayed() throws Exception {
    //Given
    userTransactions.add(userTransaction);
    userTransactions.add(userTransaction1);
    user.getFriends().add(user1);

    when(userService.getUser(1L)).thenReturn(java.util.Optional.ofNullable(user));
    when(userTransactionService.getAllTransactions(1L, PageRequest.of(0,3)))
        .thenReturn(new PageImpl<>(userTransactions));

    //When
    mockMvc.perform(get("/1/user_transactions"))
        .andExpect(status().isOk())
        .andExpect(view().name("userTransactions"))

        .andExpect(content().string(containsString("Hi, <span>Tony</span>")))

        //User send money to friend
        .andExpect(content().string(containsString("Steve Rogers")))
        .andExpect(content().string(containsString("New user transaction")))
        .andExpect(content().string(containsString("-30.0")))

        //User receive money from friend
        .andExpect(content().string(containsString("Steve Rogers")))
        .andExpect(content().string(containsString("One more new user transaction")))
        .andExpect(content().string(containsString("30.0")));
  }

  @Test
  void givenAUserWithoutUserTransactionWhenShowUserTransactionThenNoTransactionDisplayed() throws Exception {
    //Given
    User user=new User("John","Doe","john@test.com","pw","bank",100.);

    when(userService.getUser(1L)).thenReturn(java.util.Optional.of(user));
    when(userTransactionService.getAllTransactions(1L, PageRequest.of(0,3)))
        .thenReturn(Page.empty());

    //When
    mockMvc.perform(get("/1/user_transactions"))
        .andExpect(status().isOk())
        .andExpect(view().name("userTransactions"))

        .andExpect(content().string(containsString("Hi, <span>John</span>")))

        .andExpect(content().string(containsString("No transaction yet")));
  }

  @Test
  void givenAUserWithTwoBankTransactionWhenShowBankTransactionThenTwoTransactionsDisplayed() throws Exception {
    //Given
    bankTransactions.add(bankTransaction);
    bankTransactions.add(bankTransaction1);

    when(userService.getUser(1L)).thenReturn(java.util.Optional.ofNullable(user));
    when(bankTransactionService.getAllTransactions(1L, PageRequest.of(0,3)))
        .thenReturn(new PageImpl<>(bankTransactions));

    //When
    mockMvc.perform(get("/1/bank_transactions"))
        .andExpect(status().isOk())
        .andExpect(view().name("bankTransactions"))

        .andExpect(content().string(containsString("Hi, <span>Tony</span>")))

        //User send money to his bank account
        .andExpect(content().string(
            containsString("New bank transaction")))
        .andExpect(content().string(containsString("-130.")))

        //User receive money from his bank account
        .andExpect(content().string(
            containsString("One more new bank transaction")))
        .andExpect(content().string(containsString("230.")));
  }

  @Test
  void givenAUserWithoutBankTransactionWhenShowBankTransactionsThenNoTransactionDisplayed() throws Exception {
    //Given
    User user=new User("John","Doe","john@test.com","pw","bank",100.);

    when(userService.getUser(1L)).thenReturn(java.util.Optional.of(user));
    when(bankTransactionService.getAllTransactions(1L, PageRequest.of(0,3)))
        .thenReturn(Page.empty());

    //When
    mockMvc.perform(get("/1/bank_transactions"))
        .andExpect(status().isOk())
        .andExpect(view().name("bankTransactions"))

        .andExpect(content().string(containsString("Hi, <span>John</span>")))
        .andExpect(content().string(containsString("No transaction yet")));

  }
}