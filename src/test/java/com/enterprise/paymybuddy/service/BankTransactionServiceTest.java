package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.BankTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.BankTransactionRepository;
import com.enterprise.paymybuddy.jpa.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BankTransactionServiceTest {

  private final BankTransactionRepository transactionRepository =mock(BankTransactionRepository.class);
  private final UserRepository userRepository=mock(UserRepository.class);
  private final BankTransactionServiceImpl classUnderTest=new BankTransactionServiceImpl(transactionRepository,userRepository);

  User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);

  BankTransaction transaction=new BankTransaction(1L,user,"bank transaction test",5.);
  BankTransaction transaction1=new BankTransaction(2L,user,"one more bank transaction test",5.);
  List<BankTransaction>transactions=new ArrayList<>();

  @BeforeEach
  void setUp() {
  user.setUserId(1L);
  }

  @Test
  void givenAUserWithTwoBankTransactionsWhenGetAllTransactionsThenTwoTransactionsFound() {
    //Given
    user.getBankTransactions().add(transaction);
    user.getBankTransactions().add(transaction1);

    transactions.add(transaction);
    transactions.add(transaction1);

    Page<BankTransaction> page=new PageImpl<>(transactions);
    when(transactionRepository.findBankTransactionsByUserUserId(any(),any(PageRequest.class)))
        .thenReturn(page);

    //When
    Page<BankTransaction>actual= classUnderTest.getAllTransactions(1L,PageRequest.of(0,3));

    //Then
    assertThat(actual.getTotalElements()).isEqualTo(2);
    verify(transactionRepository,times(1))
        .findBankTransactionsByUserUserId(1L, PageRequest.of(0,3));
  }

  @Test
  void givenAUserWithoutBankTransactionWhenGetAllTransactionThenPageIsEmpty() {
    //Given
    when(transactionRepository.findBankTransactionsByUserUserId(any(),any(PageRequest.class)))
        .thenReturn(Page.empty());

    //When
    Page<BankTransaction> actual= classUnderTest.getAllTransactions(1L,PageRequest.of(0,3));

    //Then
    assertThat(actual.getTotalElements()).isEqualTo(0);
    verify(transactionRepository,times(1))
        .findBankTransactionsByUserUserId(1L, PageRequest.of(0,3));
  }

  @Test
  void givenAUserWithEnoughMoneyWhenSendMoneyToHisBankThenTransactionIsTrue() {
    //Given
    BankTransaction newTransaction=new BankTransaction(1L,user,"send transaction",90.);
    BankTransactionCreationDTO transactionDTO=new BankTransactionCreationDTO(1L,"send","send transaction",90.);

    when(userRepository.getById(any())).thenReturn(user);
    when(userRepository.save(any())).thenReturn(user);
    when(transactionRepository.save(any())).thenReturn(newTransaction);

    //When
    boolean actual= classUnderTest.createTransaction(transactionDTO);

    //Then
    assertTrue(actual);
  }

  @Test
  void givenAUserWithNotEnoughMoneyWhenSendMoneyToHisBankThenTransactionIsTrue() {
    //Given
    BankTransaction newTransaction=new BankTransaction(1L,user,"send transaction",1000.);
    BankTransactionCreationDTO transactionDTO=new BankTransactionCreationDTO(1L,"send","send transaction",1000.);

    when(userRepository.getById(any())).thenReturn(user);
    when(userRepository.save(any())).thenReturn(user);
    when(transactionRepository.save(any())).thenReturn(newTransaction);

    //When
    boolean actual= classUnderTest.createTransaction(transactionDTO);

    //Then
    assertFalse(actual);
  }

  @Test
  void givenAUserWhenReceiveMoneyFromHisBankThenTransactionIsTrue() {
    //Given
    BankTransaction newTransaction=new BankTransaction(1L,user,"receive transaction",1000.);
    BankTransactionCreationDTO transactionDTO=new BankTransactionCreationDTO(1L,"receive","receive transaction",1000.);

    when(userRepository.getById(any())).thenReturn(user);
    when(userRepository.save(any())).thenReturn(user);
    when(transactionRepository.save(any())).thenReturn(newTransaction);

    //When
    boolean actual= classUnderTest.createTransaction(transactionDTO);

    //Then
    assertTrue(actual);
  }

}