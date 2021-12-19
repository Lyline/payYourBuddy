package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.BankTransaction;
import com.enterprise.paymybuddy.entity.User;
import com.enterprise.paymybuddy.jpa.BankTransactionRepository;
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

class BankTransactionServiceTest {

  private BankTransactionRepository repository=mock(BankTransactionRepository.class);
  private BankTransactionServiceImpl classUnderTest=new BankTransactionServiceImpl(repository);

  User user=new User("Tony","Stark","tony@test.com","pw","bank",100.);

  BankTransaction transaction=new BankTransaction(1L,user,"bank transaction test",5.);
  BankTransaction transaction1=new BankTransaction(2L,user,"one more bank transaction test",5.);
  List<BankTransaction>transactions=new ArrayList<>();

  @BeforeEach
  void setUp() {
  user.setUserId(1L);

  user.getBankTransactions().add(transaction);
  user.getBankTransactions().add(transaction1);

  transactions.add(transaction);
  transactions.add(transaction1);
  }

  @Test
  void givenAnUserWithTwoBankTransactionsWhenGetAllTransactionsThenTwoTransactionsFound() {
    //Given
    Page<BankTransaction> page=new PageImpl<>(transactions);
    when(repository.findBankTransactionsByUserUserId(any(),any(PageRequest.class)))
        .thenReturn(page);

    //When
    Page<BankTransaction>actual= classUnderTest.getAllTransactions(1l,PageRequest.of(0,3));

    //Then
    assertThat(actual.getTotalElements()).isEqualTo(2);
    verify(repository,times(1))
        .findBankTransactionsByUserUserId(1L, PageRequest.of(0,3));
  }

  @Test
  void givenAnUserWithoutBankTransactionWhenGetAllTransactionThenPageIsEmpty() {
    //Given
    when(repository.findBankTransactionsByUserUserId(any(),any(PageRequest.class)))
        .thenReturn(Page.empty());

    //When
    Page<BankTransaction> actual= classUnderTest.getAllTransactions(1l,PageRequest.of(0,3));

    //Then
    assertThat(actual.getTotalElements()).isEqualTo(0);
    verify(repository,times(1))
        .findBankTransactionsByUserUserId(1L, PageRequest.of(0,3));
  }
}