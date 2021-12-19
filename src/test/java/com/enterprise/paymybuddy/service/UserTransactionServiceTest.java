package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.entity.UserTransaction;
import com.enterprise.paymybuddy.jpa.UserTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserTransactionServiceTest {

  private UserTransactionRepository repository=mock(UserTransactionRepository.class);
  private UserTransactionServiceImpl classUnderTest=new UserTransactionServiceImpl(repository);

  User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);
  User user1=new User("Steve","Rogers","steve@test.com","pw","bank",100.);

  UserTransaction transaction=new UserTransaction(1L,user,user1,"user transaction test",10.);
  UserTransaction transaction1=new UserTransaction(2L,user1,user,"one more user transaction test",10.);
  List<UserTransaction>transactions=new ArrayList<>();

  @BeforeEach
  void setUp() {
    user.setUserId(1L);
    user1.setUserId(2L);

    user.getUserTransactions().add(transaction);
    user.getUserTransactions().add(transaction1);

    transactions.add(transaction);
    transactions.add(transaction1);
  }

  @Test
  void givenAnUserWithTwoUserTransactionsWhenGetAllTransactionsThenTwoTransactionsFound() {
    //Given
    Page<UserTransaction>page=new PageImpl<>(transactions);

    when(repository.findUserTransactionsByCreditor_UserIdOrDebtor_UserId(any(),any(),any(PageRequest.class)))
        .thenReturn(page);

    //When
    Page<UserTransaction> actual=classUnderTest.getAllTransactions(1L, PageRequest.of(0,3));

    //Then
    assertThat(actual.getTotalElements()).isEqualTo(2);
    verify(repository,times(1))
        .findUserTransactionsByCreditor_UserIdOrDebtor_UserId(1L,1L,PageRequest.of(0,3));
  }

  @Test
  void givenAnUserWithoutUserTransactionWhenGetAllTransactionThenPageIsEmpty() {
    //Given
    when(repository.findUserTransactionsByCreditor_UserIdOrDebtor_UserId(any(),any(),any(PageRequest.class)))
        .thenReturn(Page.empty());

    //When
    Page<UserTransaction> actual= classUnderTest.getAllTransactions(1L,PageRequest.of(0,3));

    //Then
    assertThat(actual.getTotalElements()).isEqualTo(0);
    verify(repository,times(1))
        .findUserTransactionsByCreditor_UserIdOrDebtor_UserId(1L,1L,PageRequest.of(0,3));
  }
}