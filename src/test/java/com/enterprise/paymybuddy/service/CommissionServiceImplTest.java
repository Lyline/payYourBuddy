package com.enterprise.paymybuddy.service;

import com.enterprise.paymybuddy.entity.Commission;
import com.enterprise.paymybuddy.jpa.CommissionRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommissionServiceImplTest {

  private final CommissionRepository repository=mock(CommissionRepository.class);
  private final CommissionServiceImpl classUnderTest=new CommissionServiceImpl(repository);

  @Test
  void givenATransactionEqualToZeroWhenCalculateThenReturnCommissionEqualToZero() {
    //Given
    double transactionValue=0.;

    //When
    Commission actual= classUnderTest.calculate(transactionValue);

    //Then
    assertThat(actual.getValue()).isEqualTo(0);
  }

  @Test
  void givenATransactionEqualToTenWhenCalculateThenReturnCommissionEqualToZeroPointZeroFive() {
    //Given
    double transactionValue = 10;

    //When
    Commission actual = classUnderTest.calculate(transactionValue);

    //Then
    assertThat(actual.getValue()).isEqualTo(0.05);
  }

  @Test
  void givenATransactionEqualToElevenWhenCalculateThenReturnCommissionEqualToZeroPointZeroSix() {
    //Given
    double transactionValue = 11;

    //When
    Commission actual = classUnderTest.calculate(transactionValue);

    //Then
    //Commission is equal to 0.055 and rounded 0.06
    assertThat(actual.getValue()).isEqualTo(0.06);
  }

  @Test
  void givenATransactionEqualToTenPointFiveWhenCalculateThenReturnCommissionEqualToZeroPointZeroSix() {
    //Given
    double transactionValue = 10.5;

    //When
    Commission actual = classUnderTest.calculate(transactionValue);

    //Then
    //Commission is equal to 0.0525 and rounded 0.06
    assertThat(actual.getValue()).isEqualTo(0.06);
  }

  @Test
  void givenATransactionEqualToZeroPointFiveWhenCalculateCommissionThenCommissionEqualToZeroPointOne() {
    //Given
    double transactionValue=0.5;

    //When
    Commission actual=classUnderTest.calculate(transactionValue);

    //Then
    //Commission is equal to 0.0025 and rounded at 0.01
    assertThat(actual.getValue()).isEqualTo(0.01);
  }

  @Test
  void givenACommissionWhenSaveThenCommissionSaved() {
    //Given
    Commission commission=new Commission(10);
    when(repository.save(any())).thenReturn(commission);

    //When
    Commission actual= classUnderTest.save(commission);

    //Then
    assertThat(actual.getValue()).isEqualTo(10);
  }
}