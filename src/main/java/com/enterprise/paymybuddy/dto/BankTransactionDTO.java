package com.enterprise.paymybuddy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 Defines the bank transaction DTO

 @version 0.1

 @see com.enterprise.paymybuddy.entity.BankTransaction
 */
@Getter
@Setter
@RequiredArgsConstructor
public class BankTransactionDTO {
  /**
   The transaction id in the database.
   */
  private Long id;

  /**
   The description of transaction.
   */
  private String description;

  /**
   The value of transaction. If it's positive then it's a type's transaction receive, if it's negative then it's a type's
   transaction send.
   */
  private double value;
}
