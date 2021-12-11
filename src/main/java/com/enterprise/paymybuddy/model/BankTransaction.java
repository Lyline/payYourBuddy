package com.enterprise.paymybuddy.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BankTransaction {

  private Long id;
  private Long userId;
  private String accountNumber;
  private String description;
  private double value;

  public BankTransaction(Long id, Long userId, String accountNumber, String description, double value) {
    this.id = id;
    this.userId = userId;
    this.accountNumber = accountNumber;
    this.description = description;
    this.value = value;
  }
}
