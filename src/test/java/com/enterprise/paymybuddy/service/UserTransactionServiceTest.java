package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.dto.UserTransactionCreationDTO;
import com.enterprise.paymybuddy.entity.Commission;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.jpa.UserRepository;
import com.enterprise.paymybuddy.jpa.UserTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserTransactionServiceTest {

  private final UserTransactionRepository transactionRepository =mock(UserTransactionRepository.class);
  private final UserRepository userRepository= mock(UserRepository.class);
  private final CommissionServiceImpl commissionService=mock(CommissionServiceImpl.class);

  private final UserTransactionServiceImpl classUnderTest=
      new UserTransactionServiceImpl(transactionRepository, userRepository,commissionService);

  User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
  User user1=new User("Steve","Rogers","steve@test.com","pw","bank",100.);

  UserTransaction transaction=new UserTransaction(1L,user,user1,"user transaction test",10., new Commission());
  UserTransaction transaction1=new UserTransaction(2L,user1,user,"one more user transaction test",10., new Commission());
  List<UserTransaction>transactions=new ArrayList<>();

  @BeforeEach
  void setUp() {
    user.setUserId(1L);
    user1.setUserId(2L);
  }

  @Test
  void givenAnUserWithTwoUserTransactionsWhenGetAllTransactionsThenTwoTransactionsFound() {
    //Given
    user.getUserTransactions().add(transaction);
    user.getUserTransactions().add(transaction1);

    transactions.add(transaction);
    transactions.add(transaction1);

    Page<UserTransaction>page=new PageImpl<>(transactions);

    when(transactionRepository.findUserTransactionsByCreditor_UserIdOrDebtor_UserIdOrderByTransactionIdDesc(any(),any(),any(PageRequest.class)))
        .thenReturn(page);

    //When
    Page<UserTransaction> actual=classUnderTest.getAllTransactions(1L, PageRequest.of(0,3));

    //Then
    assertThat(actual.getTotalElements()).isEqualTo(2);
    verify(transactionRepository,times(1))
        .findUserTransactionsByCreditor_UserIdOrDebtor_UserIdOrderByTransactionIdDesc(1L,1L,PageRequest.of(0,3));
  }

  @Test
  void givenAnUserWithoutUserTransactionWhenGetAllTransactionThenPageIsEmpty() {
    //Given
    when(transactionRepository.findUserTransactionsByCreditor_UserIdOrDebtor_UserIdOrderByTransactionIdDesc(any(),any(),any(PageRequest.class)))
        .thenReturn(Page.empty());

    //When
    Page<UserTransaction> actual= classUnderTest.getAllTransactions(1L,PageRequest.of(0,3));

    //Then
    assertThat(actual.getTotalElements()).isEqualTo(0);
    verify(transactionRepository,times(1))
        .findUserTransactionsByCreditor_UserIdOrDebtor_UserIdOrderByTransactionIdDesc(1L,1L,PageRequest.of(0,3));
  }

  @Test
  void givenAnUserWithTwoTransactionsWhenGetLastTransactionThenReturnTheLastTransactionRecorded() {
    //Given
    List<UserTransaction>transactions=new ArrayList<>();
    transactions.add(transaction);
    transactions.add(transaction1);

    when(transactionRepository.findUserTransactionsByCreditor_UserIdOrDebtor_UserId(anyLong(),anyLong()))
        .thenReturn(transactions);

    //When
    UserTransaction actual= classUnderTest.getLastTransaction(1L);

    //Then
    assertThat(actual).isEqualTo(transaction1);
  }

  @Test
  void givenAnUserWithoutTransactionWhenGetLastTransactionThenReturnNewTransaction() {
    //Given
    List<UserTransaction>transactions=new ArrayList<>();

    when(transactionRepository.findUserTransactionsByCreditor_UserIdOrDebtor_UserId(anyLong(),anyLong()))
        .thenReturn(transactions);

    //When
    UserTransaction actual= classUnderTest.getLastTransaction(1L);

    //Then
    assertThat(actual.getTransactionId()).isEqualTo(null);
  }

  @Test
  void givenADebtorWithMoneyAndACreditorWhenCreateTransactionThenTransactionSaved() {
    //Given
    UserTransactionCreationDTO transactionDTO=new UserTransactionCreationDTO();
      transactionDTO.setDebtorId(1L);
      transactionDTO.setCreditorId(2L);
      transactionDTO.setDescription("New Transaction");
      transactionDTO.setValue(10.);

    Commission commission=new Commission(0.05);

    UserTransaction transaction=new UserTransaction();
    transaction.setDebtor(user);
    transaction.setCreditor(user1);
    transaction.setDescription(transactionDTO.getDescription());
    transaction.setValue(transactionDTO.getValue());

    when(userRepository.getById(transactionDTO.getDebtorId())).thenReturn(user);
    when(userRepository.getById(transactionDTO.getCreditorId())).thenReturn(user1);
    when(commissionService.calculate(anyDouble())).thenReturn(commission);

    when(transactionRepository.save(any())).thenReturn(transaction);
    when(commissionService.save(any())).thenReturn(commission);

    //When
    classUnderTest.createTransaction(transactionDTO);

    //Then
    //Balance's debtor : current value= transaction value - commission
    assertThat(user.getBalance()).isEqualTo(89.95);
    assertThat(user1.getBalance()).isEqualTo(110.);
  }

  @Test
  void givenADebtorWithoutMoneyAndACreditorWhenCreateTransactionThenReturnException() {
    //Given
    user.setBalance(0.);

    UserTransactionCreationDTO transactionDTO=new UserTransactionCreationDTO();
    transactionDTO.setDebtorId(1L);
    transactionDTO.setCreditorId(2L);
    transactionDTO.setDescription("New Transaction");
    transactionDTO.setValue(10.);

    Commission commission=new Commission(0.05);

    UserTransaction transaction=new UserTransaction();
    transaction.setDebtor(user);
    transaction.setCreditor(user1);
    transaction.setDescription(transactionDTO.getDescription());
    transaction.setValue(transactionDTO.getValue());

    when(userRepository.getById(transactionDTO.getDebtorId())).thenReturn(user);
    when(userRepository.getById(transactionDTO.getCreditorId())).thenReturn(user1);
    when(commissionService.calculate(anyDouble())).thenReturn(commission);

    when(transactionRepository.save(any())).thenReturn(transaction);
    when(commissionService.save(any())).thenReturn(commission);

    //When
    boolean actual=classUnderTest.createTransaction(transactionDTO);

    //Then
    assertFalse(actual);
  }
}